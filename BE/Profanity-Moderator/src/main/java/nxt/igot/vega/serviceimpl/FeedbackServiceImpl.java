package nxt.igot.vega.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; 

import com.fasterxml.jackson.databind.ObjectWriter;

import nxt.igot.vega.entity.ImageFeedbackEntity;
import nxt.igot.vega.entity.TextFeedbackEntity;
import nxt.igot.vega.model.AITextRequest;
import nxt.igot.vega.model.AITextResponse;
import nxt.igot.vega.model.Feedback;
import nxt.igot.vega.model.FeedbackResponse;
import nxt.igot.vega.model.Filter;
import nxt.igot.vega.model.TextClassificationResult;
import nxt.igot.vega.repo.ImageFeedbackRepo;
import nxt.igot.vega.repo.TextFeedbackRepo;
import nxt.igot.vega.service.FeedbackService;
import nxt.igot.vega.service.FileService;
import nxt.igot.vega.util.CustomObjectMapper;
 
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Value(value = "${spring.kafka.topicname.moderated}")
	String moderated;
	@Value(value = "${spring.kafka.topicname.flagged}")
	String flagged;
	@Value(value = "${spring.kafka.topicname.moderatedAI}")
	String moderatedAI;
	@Value(value = "${spring.kafka.topicname.flaggedAI}")
	String flaggedAI;
	@Value(value = "${spring.kafka.groupid}")
	String groupid;
	
	@Value(value = "${profanity.classification.offensive}")
	String offensive;
	@Value(value = "${profanity.classification.probabilityCutoff}")
	Float probabilityCutoff;
	
	Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	@Autowired
	TextFeedbackRepo textFeedbackRepo;
	@Autowired
	ImageFeedbackRepo imageFeedbackRepo;
	
	@Autowired
	FileService fileService;

	@Override
	public FeedbackResponse fetchUserTextFeedback(String sortBy, String order, Integer page, 
			Integer size, boolean isModerated, Filter filter) 
			throws IOException {
		
		Pageable pageableRequest = PageRequest.of(page, size);
		List<TextFeedbackEntity> dataList = orderFilter(isModerated, pageableRequest, sortBy, order, filter);
		List<Feedback> feedbackList = new ArrayList<>();
		for (TextFeedbackEntity data : dataList) {
			feedbackList.add(CustomObjectMapper.textFeedbackentityToFeedback(data));
		}
		return new FeedbackResponse(feedbackList);
	}
	
	private List<TextFeedbackEntity> orderFilter(boolean isModerated, Pageable pageableRequest, 
			String sortBy, String order, Filter filter){
		if(filter!=null && filter.getField().equals("flaggedBy")) {
				if(sortBy!=null && sortBy.equals("timestamp")) {
					if(order.equals("asc")) {
						return textFeedbackRepo.findByModeratedAndFlagOrderByTimestampAsc(isModerated, filter.getValue(), pageableRequest);
					}else {
						return textFeedbackRepo.findByModeratedAndFlagOrderByTimestampDesc(isModerated, filter.getValue(), pageableRequest);
					}
				}
			
		}else {
			if(sortBy!=null && sortBy.equals("timestamp")) {
				if(order.equals("asc")) {
					return textFeedbackRepo.findByModeratedOrderByTimestampAsc(isModerated, pageableRequest);
				}else {
					return textFeedbackRepo.findByModeratedOrderByTimestampDesc(isModerated, pageableRequest);
				}
			}
		}
		
		return textFeedbackRepo.findByModerated(isModerated, pageableRequest);
	}

	@Override
	public String persistFeedback(FeedbackResponse data){
		List<TextFeedbackEntity> moderatedFeedback = new ArrayList<>();
		for (Feedback feedback : data.getFeedbackList()) {
			if(feedback.getId()==null) {
				return "missing event id in request";
			}

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				String json = ow.writeValueAsString(feedback);
				ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(moderated, feedback.getContentId(), json.toString());
				logger.info(String.format("Event pushed to moderated topic -> %s", json.toString()));
				Optional<TextFeedbackEntity> result = textFeedbackRepo.findById(feedback.getId());
				if(result.isPresent()) {
					TextFeedbackEntity feedbackEntity = result.get();
					feedbackEntity.setClassification(feedback.getClassification());
					feedbackEntity.setComment(feedback.getComment());
					feedbackEntity.setProfane(feedback.getProfaneStrings());
					feedbackEntity.setReason(feedback.getReason());
					feedbackEntity.setModerationTimestamp(Long.toString(System.currentTimeMillis()));
					feedbackEntity.setFlag(feedback.getFlaggedBy());
					feedbackEntity.setProbability(feedback.getProbability());
					feedbackEntity.setProfanelineAnalysis(feedback.getProfanelineAnalysis());
					
					//if moderated by AI, checking confidence
					if(feedbackEntity.getFlag().equals("AI_flagged") && feedbackEntity.getProbability()!=null 
							&& feedbackEntity.getProbability()<probabilityCutoff) {
						feedbackEntity.setModerated(false);
					}else {
						feedbackEntity.setModerated(true);
					}
					
					moderatedFeedback.add(feedbackEntity);
				}else {
					return "no event found for given event id";
				}

			} catch (JsonProcessingException e) {
				logger.error(e.getCause().getMessage());
				return "malformed event found with given id";

			}

			
		}
		textFeedbackRepo.saveAll(moderatedFeedback);
		
		return "Data Moderated";
	}

	@KafkaListener(topics = "${spring.kafka.topicname.flagged}", groupId = "${spring.kafka.groupid}")
	public void consumeFlagged(String feed) {
		Feedback feedback;
		try {
			feedback = new ObjectMapper().readValue(feed, Feedback.class);
			logger.info(String.format("Event obtained for moderation-> %s", feedback));
			logger.info(String.format("flagged topic, raw feed-> %s", feed));
			if(feedback.getText()!=null && !feedback.getText().isEmpty()) {
				TextFeedbackEntity feedbackEntity = textFeedbackRepo.save(CustomObjectMapper.feedbackToTextFeedbackentity(feedback, false, false, null));
				logger.info(String.format("Event persisted in ES with id-> %s", feedbackEntity.getId()));
				// sending content with header for AI moderation
				AITextRequest payload = new AITextRequest(feedbackEntity.getHeader() + ". " +feedbackEntity.getText(), feedbackEntity.getId());
				kafkaTemplate.send(flaggedAI, feedbackEntity.getId(), payload.toString());
				logger.info(String.format("Event pushed to flaggedAI topic -> %s", payload.toString()));
			}else {
				ImageFeedbackEntity feedbackEntity = imageFeedbackRepo.save(CustomObjectMapper.feedbackToImageFeedbackentity(feedback, false, false, null));
				logger.info(String.format("feedback persisted with id-> %s", feedbackEntity.getId()));
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		
	}
	
	@KafkaListener(topics = "${spring.kafka.topicname.moderatedAI}", groupId = "${spring.kafka.groupid}")
	public void consumeModerated(String feed) {
		logger.info(String.format("Event obtained after moeration from AI-> %s", feed));
		try {
			AITextResponse response = new ObjectMapper().readValue(feed, AITextResponse.class);
	
			logger.info(String.format("moderated event mapped to AITextResponse -> %s", response.getKey()));
			FeedbackResponse feedbackResponse = mapAiResponseToFeedback(response);
			logger.info(String.format("moderated AI response mapped to FeedbackResponse -> %s", 
					feedbackResponse.getFeedbackList().get(0).getId()));
			persistFeedback(feedbackResponse);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	private FeedbackResponse mapAiResponseToFeedback(AITextResponse aiResponse) {
		List<Feedback> feedbackList = new ArrayList<>();
		Optional<TextFeedbackEntity> result = textFeedbackRepo.findById(aiResponse.getKey());
		if(result.isPresent()) {
			TextFeedbackEntity entity = result.get();
			
			String profanityDeterminedByAI = ascertainProfanityFromAI(aiResponse);
			
			
			Feedback feedback = CustomObjectMapper.textFeedbackentityToFeedback(entity);
			feedback.addProfaneStrings(aiResponse.getPayload().getPossible_profanity());
			feedback.setClassification(profanityDeterminedByAI);
			feedback.setFlaggedBy("AI_flagged");
			feedback.setModerationtimestamp(Long.toString(System.currentTimeMillis()));
			List<String> reason = new ArrayList<>();
			reason.add(aiResponse.getPayload().getOverall_text_classification().getClassification());
			feedback.setReason(reason);
			feedback.setProbability(aiResponse.getPayload().getOverall_text_classification().getProbability());
			feedback.setProfanelineAnalysis(aiResponse.getPayload().getLine_analysis());
			if(profanityDeterminedByAI.equals("NSFW")) {
				feedback.setPublished(false);			
			}else{
				feedback.setPublished(true);			
			}
			feedbackList.add(feedback);
			
		}
		
		return new FeedbackResponse(feedbackList);
	}
	
	public String ascertainProfanityFromAI(AITextResponse aiResponse) {
		boolean lineAnalysis = false;
		for(TextClassificationResult line : aiResponse.getPayload().getLine_analysis()) {
			if(line.getProbability()>probabilityCutoff && line.getClassification().equals(offensive)) {
				lineAnalysis = true;
			}
		}
		if(	aiResponse.getPayload().getOverall_text_classification().getClassification().equals(offensive) ||
				!aiResponse.getPayload().getPossible_profanity().isEmpty() ||
				lineAnalysis
				) {
			return "NSFW";
		}
		return "SFW";
	}

	@Override
	public String mockFeedback(Feedback data) {


		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(data);
			ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(flagged,data.getContentId(), json.toString());


		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}


		return "Feedback pushed to topic";
	}

	@Override
	public FeedbackResponse fetchUserImageFeedback(Integer page, Integer size) {
		List<ImageFeedbackEntity> result = imageFeedbackRepo.findByModerated(false);
		List<Feedback> response = new ArrayList<>();
		for(ImageFeedbackEntity r: result) {
			response.add(CustomObjectMapper.imageFeedbackentityToFeedback(r));
		}
		return new FeedbackResponse(response);
	}

	@Override
	public String persistImageFeedback(FeedbackResponse data) {
		List<ImageFeedbackEntity> moderatedFeedback = new ArrayList<>();
		for (Feedback feedback : data.getFeedbackList()) {

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				String json = ow.writeValueAsString(feedback);
				ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(moderated,feedback.getContentId(), json.toString());
				List<ImageFeedbackEntity> result = imageFeedbackRepo.findByUrl(feedback.getUrl());
				if(result!=null && !result.isEmpty()) {
					ImageFeedbackEntity feedbackEntity = result.get(0);
					feedbackEntity.setModerated(true);
					feedbackEntity.setModerationTimestamp(Long.toString(System.currentTimeMillis()));
					moderatedFeedback.add(feedbackEntity);
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());

			}

		
		}
		imageFeedbackRepo.saveAll(moderatedFeedback);
		
		return "Data Moderated";
	}

	@Override
	public String persistImageFeedbackModified(FeedbackResponse data, MultipartFile file) {
		List<ImageFeedbackEntity> moderatedFeedback = new ArrayList<>();
		
		//upload modified file to storage
		String newImageUrl = fileService.uploadImage(file);
		
		for (Feedback feedback : data.getFeedbackList()) {

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				String json = ow.writeValueAsString(feedback);
				ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(moderated,feedback.getContentId(), json.toString());
				List<ImageFeedbackEntity> result = imageFeedbackRepo.findByUrl(feedback.getUrl());
				if(result!=null && !result.isEmpty()) {
					ImageFeedbackEntity feedbackEntity = result.get(0);
					feedbackEntity.setModerated(true);
					feedbackEntity.setUrl(newImageUrl);
					moderatedFeedback.add(feedbackEntity);
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();

			}

			
		}
		imageFeedbackRepo.saveAll(moderatedFeedback);
		
		return "Data Moderated";
	}

	@Override
	public List<String> fetchDistinctFlags() {
		return textFeedbackRepo.finddistinctFlags();
	}

}
