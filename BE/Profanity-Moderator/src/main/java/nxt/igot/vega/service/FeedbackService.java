package nxt.igot.vega.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nxt.igot.vega.model.Feedback;
import nxt.igot.vega.model.FeedbackResponse;
import nxt.igot.vega.model.Filter;

public interface FeedbackService {

	public FeedbackResponse fetchUserTextFeedback(String sortBy,String order, Integer page, Integer size, boolean isModerated, Filter filter) 
			throws IOException;


	public String persistFeedback(FeedbackResponse data);


	public String mockFeedback(Feedback data);


	public FeedbackResponse fetchUserImageFeedback(Integer page, Integer size);


	public String persistImageFeedback(FeedbackResponse data);


	public String persistImageFeedbackModified(FeedbackResponse data, MultipartFile file);


	public List<String> fetchDistinctFlags();
	
}
