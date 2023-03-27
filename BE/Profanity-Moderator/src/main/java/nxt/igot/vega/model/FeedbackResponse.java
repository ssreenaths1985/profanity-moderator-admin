package nxt.igot.vega.model;

import java.util.List;

public class FeedbackResponse {
	List<Feedback> feedbackList;

	public FeedbackResponse() {
		super();
	}

	public FeedbackResponse(List<Feedback> feedbackList) {
		super();
		this.feedbackList = feedbackList;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
}
