package nxt.igot.vega.model;

import java.util.List;

import nxt.igot.vega.util.FeedbckType;

public class Feedback {

	String text;
	String heading;
	FeedbckType type;
	List<String> profaneStrings;
	String classification;
	String id;
	String flaggedBy;
	String url;
	String timestamp;
	String author;
	String feedbackOriginPlatform;
	String feedbackOriginCategory;
	String moderationtimestamp;
	String comment;
	boolean published;
	boolean moderated;
	String contentId;
	String raw;
	List<String> reason;
	Float probability;
	List<TextClassificationResult> profanelineAnalysis;

	public Feedback(String text, FeedbckType type, List<String> profaneStrings, String classification, String id,
			String flaggedBy, String url, String timestamp, String author, String feedbackOriginPlatform,
			String feedbackOriginCategory, String moderationtimestamp, boolean moderated, boolean published,
			String comment, String contentId, String raw, 
			List<String> reason, String heading, Float probability,
			List<TextClassificationResult> profanelineAnalysis) {
		super();
		this.text = text;
		this.type = type;
		this.profaneStrings = profaneStrings;
		this.classification = classification;
		this.id = id;
		this.flaggedBy = flaggedBy;
		this.url = url;
		this.timestamp = timestamp;
		this.author = author;
		this.feedbackOriginPlatform = feedbackOriginPlatform;
		this.feedbackOriginCategory = feedbackOriginCategory;
		this.moderationtimestamp = moderationtimestamp;
		this.moderated = moderated;
		this.published = published;
		this.comment = comment;
		this.contentId = contentId;
		this.raw = raw;
		this.reason = reason;
		this.heading = heading;
		this.probability = probability;
		this.profanelineAnalysis = profanelineAnalysis;
	}

	public List<TextClassificationResult> getProfanelineAnalysis() {
		return profanelineAnalysis;
	}

	public void setProfanelineAnalysis(List<TextClassificationResult> profanelineAnalysis) {
		this.profanelineAnalysis = profanelineAnalysis;
	}

	public Float getProbability() {
		return probability;
	}

	public void setProbability(Float probability) {
		this.probability = probability;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public List<String> getReason() {
		return reason;
	}

	public void setReason(List<String> reason) {
		this.reason = reason;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public FeedbckType getType() {
		return type;
	}

	public void setType(FeedbckType type) {
		this.type = type;
	}

	public String getModerationtimestamp() {
		return moderationtimestamp;
	}

	public void setModerationtimestamp(String moderationtimestamp) {
		this.moderationtimestamp = moderationtimestamp;
	}

	public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFeedbackOriginPlatform() {
		return feedbackOriginPlatform;
	}

	public void setFeedbackOriginPlatform(String feedbackOriginPlatform) {
		this.feedbackOriginPlatform = feedbackOriginPlatform;
	}

	public String getFeedbackOriginCategory() {
		return feedbackOriginCategory;
	}

	public void setFeedbackOriginCategory(String feedbackOriginCategory) {
		this.feedbackOriginCategory = feedbackOriginCategory;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFlaggedBy() {
		return flaggedBy;
	}

	public void setFlaggedBy(String flaggedBy) {
		this.flaggedBy = flaggedBy;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getProfaneStrings() {
		return profaneStrings;
	}

	public void setProfaneStrings(List<String> profaneStrings) {
		this.profaneStrings = profaneStrings;
	}

	public void addProfaneStrings(List<String> strings) {
		if (profaneStrings != null && !profaneStrings.isEmpty()) {
			for (String profanity : strings) {
				if (!profaneStrings.contains(profanity)) {
					profaneStrings.add(profanity);
				}
			}
		} else {
			setProfaneStrings(strings);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Feedback() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Override
	public String toString() {
		return "Feedback [text=" + text + ", profaneStrings=" + profaneStrings + ", classification=" + classification
				+ ", id=" + id + "]";
	}
}
