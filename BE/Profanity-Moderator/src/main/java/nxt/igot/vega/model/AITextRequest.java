package nxt.igot.vega.model;

public class AITextRequest {
	
	String text;
	String key;
	
	public AITextRequest(String text, String key) {
		super();
		this.text = text;
		this.key = key;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "{\"text\" : \"" + text + "\", \"key\":\"" + key + "\"}";
	}
	
	

}
