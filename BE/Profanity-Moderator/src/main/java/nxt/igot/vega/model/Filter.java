package nxt.igot.vega.model;

public class Filter {
	String field;
	String value;
	public Filter() {
		super();
	}
	public Filter(String field, String value) {
		super();
		this.field = field;
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
