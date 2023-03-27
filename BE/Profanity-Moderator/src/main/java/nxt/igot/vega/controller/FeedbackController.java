package nxt.igot.vega.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nxt.igot.vega.model.DataRequest;
import nxt.igot.vega.model.Feedback;
import nxt.igot.vega.model.FeedbackResponse;
import nxt.igot.vega.model.Status;
import nxt.igot.vega.service.FeedbackService;
import nxt.igot.vega.util.Decorator;
import nxt.igot.vega.util.ImageProfanityTypes;
import nxt.igot.vega.util.TextProfanityTypes;

//@CrossOrigin(origins = "*")
@RestController
//@RequestMapping(value = "/*", method = RequestMethod.OPTIONS)
public class FeedbackController {
	
	@Autowired
	FeedbackService feedbackService;

	Logger log = LoggerFactory.getLogger(FeedbackController.class);
	
	@GetMapping(value="/feedback/flag/values")	
	public Status<List<String>> fetchFlagValues(){
		return new Status<>(HttpStatus.OK.toString(), 200,feedbackService.fetchDistinctFlags());
	}
	
	@PostMapping(value="/feedback/text/fetch")
	public Status<FeedbackResponse> fetchFeedback(@RequestBody DataRequest data){
		try {
			return new Status<>(HttpStatus.OK.toString(), 200, 
					feedbackService.fetchUserTextFeedback(data.getSort().getField(), data.getSort().getOrder(),
							data.getPage().getNumber(), data.getPage().getSize(), data.isModerated(), data.getFilter()));
		}catch (Exception e) {
			log.error(e.getMessage());
			return new Status<>(e.getLocalizedMessage(), 500, new FeedbackResponse());
		}
	}
//	@CrossOrigin(origins="*")
	@PostMapping(value="/feedback/image/fetch")
	public Status<FeedbackResponse> fetchImageFeedback(@RequestBody DataRequest data){
		try {
			return new Status<>(HttpStatus.OK.toString(), 200, 
					feedbackService.fetchUserImageFeedback(data.getPage().getNumber(), data.getPage().getSize()));
		}catch (Exception e) {
			log.error(e.getMessage());
			return new Status<>(e.getLocalizedMessage(), 500, new FeedbackResponse());
		}
	}
//	@CrossOrigin(origins="*")
	@PostMapping(value="/feedback/persist/text/moderated")
	public Status<String> moderatedFeedback(@RequestBody FeedbackResponse data){
		try {
			return new Status<>(HttpStatus.OK.toString(), 200, feedbackService.persistFeedback(data));
		}catch (Exception e) {
			return new Status<>(e.getLocalizedMessage(), 500, e.getCause().toString());
		}
	}
	
	@CrossOrigin(origins="*")
	@PostMapping(value="/feedback/persist/image/moderated")
	public Status<String> moderatedImageFeedback(@RequestBody FeedbackResponse data){
		try {
			return new Status<>(HttpStatus.OK.toString(), 200, feedbackService.persistImageFeedback(data));
		}catch (Exception e) {
			return new Status<>(e.getLocalizedMessage(), 500, e.getCause().toString());
		}
	}
	
//	@CrossOrigin(origins="*")
	@PostMapping(value="/feedback/persist/image/moderated/modified")
	public Status<String> moderatedModifiedImageFeedback(@RequestPart("data") FeedbackResponse data, @RequestPart("file") MultipartFile file){
		try {
			return new Status<>(HttpStatus.OK.toString(), 200, feedbackService.persistImageFeedbackModified(data, file));
		}catch (Exception e) {
			return new Status<>(e.getLocalizedMessage(), 500, e.getCause().toString());
		}
	}
	
	@CrossOrigin(origins="*")
	@PostMapping(value="/feedback/flag/mock")
	public Status<String> moderatedFeedback(@RequestBody Feedback data){
		try {
			return new Status<>(HttpStatus.OK.toString(), 200, feedbackService.mockFeedback(data));
		}catch (Exception e) {
			return new Status<>(e.getLocalizedMessage(), 500, e.getCause().toString());
		}
	}
	
//	@CrossOrigin(origins="*")
	@GetMapping(value="/profanity/type/text")
	public Status<List<String>> getTextProfanityTypes(){
		try {
			TextProfanityTypes[] classes = TextProfanityTypes.values();
			List<String> categories = new ArrayList<>();
			for(TextProfanityTypes category : classes) {
				categories.add(Decorator.enumToString(category.toString()));
			}
			
			return new Status<>(HttpStatus.OK.toString(), 200, categories);
		}catch (Exception e) {
			return new Status<>(e.getLocalizedMessage(), 500, null);
		}
	}
	
//	@CrossOrigin(origins="*")
	@GetMapping(value="/profanity/type/image")
	public Status<List<String>> getImageProfanityTypes(){
		try {
			ImageProfanityTypes[] classes = ImageProfanityTypes.values();
			List<String> categories = new ArrayList<>();
			for(ImageProfanityTypes category : classes) {
				categories.add(Decorator.enumToString(category.toString()));
			}
			
			return new Status<>(HttpStatus.OK.toString(), 200, categories);
		}catch (Exception e) {
			return new Status<>(e.getLocalizedMessage(), 500, null);
		}
	}
	
	
}
