package nxt.igot.vega.model;

public class TextClassificationResult {
	String text;
	String classification;
	Float probability;
	
	public TextClassificationResult() {
		super();
	}
	public TextClassificationResult(String text, String classification, Float probability) {
		super();
		this.text = text;
		this.classification = classification;
		this.probability = probability;
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
	public Float getProbability() {
		return probability;
	}
	public void setProbability(Float probability) {
		this.probability = probability;
	}
}
