package nxt.igot.vega.model;

import java.util.List;

public class TextProfanityPayload {
	List<String> possible_profanity;
	Integer possible_profane_word_count;
	List<TextClassificationResult> line_analysis;
	TextClassificationResult overall_text_classification;
	String text_original;
	
	public TextProfanityPayload() {
		super();
	}
	public TextProfanityPayload(List<String> possible_profanity, Integer possible_profane_word_count,
			List<TextClassificationResult> line_analysis, TextClassificationResult overall_text_classification,
			String text_original) {
		super();
		this.possible_profanity = possible_profanity;
		this.possible_profane_word_count = possible_profane_word_count;
		this.line_analysis = line_analysis;
		this.overall_text_classification = overall_text_classification;
		this.text_original = text_original;
	}
	public List<String> getPossible_profanity() {
		return possible_profanity;
	}
	public void setPossible_profanity(List<String> possible_profanity) {
		this.possible_profanity = possible_profanity;
	}
	public Integer getPossible_profane_word_count() {
		return possible_profane_word_count;
	}
	public void setPossible_profane_word_count(Integer possible_profane_word_count) {
		this.possible_profane_word_count = possible_profane_word_count;
	}
	public List<TextClassificationResult> getLine_analysis() {
		return line_analysis;
	}
	public void setLine_analysis(List<TextClassificationResult> line_analysis) {
		this.line_analysis = line_analysis;
	}
	public TextClassificationResult getOverall_text_classification() {
		return overall_text_classification;
	}
	public void setOverall_text_classification(TextClassificationResult overall_text_classification) {
		this.overall_text_classification = overall_text_classification;
	}
	public String getText_original() {
		return text_original;
	}
	public void setText_original(String text_original) {
		this.text_original = text_original;
	}
	
	
}
