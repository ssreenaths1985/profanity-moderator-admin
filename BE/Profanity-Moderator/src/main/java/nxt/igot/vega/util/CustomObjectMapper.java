package nxt.igot.vega.util;

import nxt.igot.vega.entity.ImageFeedbackEntity;
import nxt.igot.vega.entity.TextFeedbackEntity;
import nxt.igot.vega.model.Feedback;

public class CustomObjectMapper{
	
	public static TextFeedbackEntity feedbackToTextFeedbackentity(Feedback feedback, boolean trained, boolean moderated, 
			String moderationTimestamp) {
		return new TextFeedbackEntity(feedback.getProfaneStrings(), feedback.getFlaggedBy(), feedback.getText(),
				trained, moderated, feedback.getAuthor(), feedback.getTimestamp(),
				moderationTimestamp, feedback.getFeedbackOriginPlatform(),
				feedback.getFeedbackOriginCategory(), feedback.getClassification(), feedback.isPublished(), 
				feedback.getComment(), feedback.getContentId(), feedback.getRaw(), feedback.getReason(), feedback.getHeading(),
				feedback.getProbability(), feedback.getProfanelineAnalysis());
	}
	
	public static ImageFeedbackEntity feedbackToImageFeedbackentity(Feedback feedback, boolean trained, boolean moderated, 
			String moderationTimestamp) {
		return new ImageFeedbackEntity(feedback.getUrl(), feedback.getFlaggedBy(), 
				trained, moderated, feedback.getAuthor(), 
				feedback.getClassification(), feedback.getTimestamp(), moderationTimestamp, 
				feedback.getFeedbackOriginPlatform(), feedback.getFeedbackOriginCategory(), feedback.isPublished(), 
				feedback.getComment(), feedback.getContentId(), feedback.getRaw(), feedback.getReason(), feedback.getHeading(),
				feedback.getProbability());
	}
	
	public static Feedback textFeedbackentityToFeedback(TextFeedbackEntity feedbackEntity) {
		return new Feedback(feedbackEntity.getText(), FeedbckType.TEXT, feedbackEntity.getProfane(), 
				feedbackEntity.getClassification(), feedbackEntity.getId(), feedbackEntity.getFlag(), 
				null, feedbackEntity.getTimestamp(), feedbackEntity.getAuthor(), 
				feedbackEntity.getFeedbackOriginPlatform(), feedbackEntity.getFeedbackOriginCategory(), 
				feedbackEntity.getModerationTimestamp(), feedbackEntity.isModerated(), feedbackEntity.isPublished(), 
				feedbackEntity.getComment(), feedbackEntity.getContentId(), feedbackEntity.getRaw(), feedbackEntity.getReason(),
				feedbackEntity.getHeader(), feedbackEntity.getProbability(), feedbackEntity.getProfanelineAnalysis());
				
	}
	
	public static Feedback imageFeedbackentityToFeedback(ImageFeedbackEntity feedbackEntity) {
		return new Feedback(null, FeedbckType.IMAGE, null, 
				feedbackEntity.getClassification(), feedbackEntity.getId(), feedbackEntity.getFlag(), 
				feedbackEntity.getUrl(), feedbackEntity.getTimestamp(), feedbackEntity.getAuthor(), 
				feedbackEntity.getFeedbackOriginPlatform(), feedbackEntity.getFeedbackOriginCategory(), 
				feedbackEntity.getModerationTimestamp(), feedbackEntity.isModerated(), feedbackEntity.isPublished(), 
				feedbackEntity.getComment(), feedbackEntity.getContentId(), feedbackEntity.getRaw(), feedbackEntity.getReason(), 
				feedbackEntity.getHeader(), feedbackEntity.getProbability(), null);
				
	}
	
	

}

