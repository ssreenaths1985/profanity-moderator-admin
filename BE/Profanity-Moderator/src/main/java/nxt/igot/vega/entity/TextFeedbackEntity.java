package nxt.igot.vega.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import nxt.igot.vega.model.TextClassificationResult;


@Document(indexName = "moderation_text_dev", type="json",createIndex = true)

public class TextFeedbackEntity {
	@ElementCollection(targetClass = String.class)
	List<String> profane;
	String flag;
	@Column(length = 10000)
	String text;
	String header;
	boolean trained = true;
	boolean moderated = true;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	String id;
	@Field(type=FieldType.Keyword)
	String classification;
	String author;
	@Field(type=FieldType.Keyword)
	String timestamp;
	String moderationTimestamp;
	String feedbackOriginPlatform;
	String feedbackOriginCategory;
	boolean published;
	String comment;
	@Column(unique=true)
	String contentId;
	String raw;
	List<String> reason;
	Float probability;
	List<TextClassificationResult> profanelineAnalysis;

	public TextFeedbackEntity(List<String> profane, String flag, String text, boolean trained, boolean moderated,
			String author, String timestamp, String moderationTimestamp, String feedbackOriginPlatform,
			String feedbackOriginCategory, String classification, boolean published,String comment,String contentId, String raw,
			List<String> reason, String header, Float probability,List<TextClassificationResult> profanelineAnalysis) {
		super();
		this.profane = profane;
		this.flag = flag;
		this.text = text;
		this.trained = trained;
		this.moderated = moderated;
		this.author = author;
		this.timestamp = timestamp;
		this.moderationTimestamp = moderationTimestamp;
		this.feedbackOriginPlatform = feedbackOriginPlatform;
		this.feedbackOriginCategory = feedbackOriginCategory;
		this.classification = classification;
		this.published = published;
		this.comment = comment;
		this.contentId = contentId;
		this.raw = raw;
		this.reason = reason;
		this.header = header;
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

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
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

	public TextFeedbackEntity() {
		super();
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


	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public List<String> getProfane() {
		return profane;
	}

	public void setProfane(List<String> profane) {
		this.profane = profane;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public boolean isTrained() {
		return trained;
	}

	public void setTrained(boolean trained) {
		this.trained = trained;
	}

	public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getModerationTimestamp() {
		return moderationTimestamp;
	}

	public void setModerationTimestamp(String moderationTimestamp) {
		this.moderationTimestamp = moderationTimestamp;
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

}
