package nxt.igot.vega.model;

import java.util.List;

import nxt.igot.vega.entity.TextFeedbackEntity;

public class FeedbackEntityList {
	List<TextFeedbackEntity> feedbackList;

	public FeedbackEntityList() {
		super();
	}

	public FeedbackEntityList(List<TextFeedbackEntity> feedbackList) {
		super();
		this.feedbackList = feedbackList;
	}

	public List<TextFeedbackEntity> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<TextFeedbackEntity> feedbackList) {
		this.feedbackList = feedbackList;
	}
}
