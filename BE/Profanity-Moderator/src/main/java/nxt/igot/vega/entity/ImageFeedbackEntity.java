package nxt.igot.vega.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import nxt.igot.vega.util.ImageProfanityTypes;

@Document(indexName = "image_moderation", createIndex = true)
public class ImageFeedbackEntity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	String id;
	String header;
	String url;
	String flag;
	boolean trained = false;
	boolean moderated = false;
	boolean published;
	String author;
	String classification;
	String timestamp;
	String moderationTimestamp;
	String feedbackOriginPlatform;
	String feedbackOriginCategory;
	String comment;
	@Column(unique=true)
	String contentId;
	String raw;
	List<String> reason;
	Float probability;
	
	public ImageFeedbackEntity(String url, String flag, boolean trained, boolean moderated,
			String author, String classification, String timestamp, String moderationTimestamp,
			String feedbackOriginPlatform, String feedbackOriginCategory, boolean published,String comment,String contentId, String raw,
			List<String> reason, String header,Float probability) {
		super();
		this.url = url;
		this.flag = flag;
		this.trained = trained;
		this.moderated = moderated;
		this.author = author;
		this.classification = classification;
		this.timestamp = timestamp;
		this.moderationTimestamp = moderationTimestamp;
		this.feedbackOriginPlatform = feedbackOriginPlatform;
		this.feedbackOriginCategory = feedbackOriginCategory;
		this.published = published;
		this.comment = comment;
		this.contentId = contentId;
		this.raw = raw;
		this.reason = reason;
		this.header = header;
		this.probability = probability;
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
	public void setId(String id) {
		this.id = id;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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

	public ImageFeedbackEntity() {
		super();
	}
}
