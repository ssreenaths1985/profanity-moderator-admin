package nxt.igot.vega.model;

public class Sort {
	String field;
	String order;
	public Sort() {
		super();
	}
	public Sort(String field, String order) {
		super();
		this.field = field;
		this.order = order;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
