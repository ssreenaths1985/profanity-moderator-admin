package nxt.igot.vega.model;

public class AITextResponse {
	String key;
	TextProfanityPayload payload;
	public AITextResponse(String key, TextProfanityPayload payload) {
		super();
		this.key = key;
		this.payload = payload;
	}
	public AITextResponse() {
		super();
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public TextProfanityPayload getPayload() {
		return payload;
	}
	public void setPayload(TextProfanityPayload payload) {
		this.payload = payload;
	}
	
}
