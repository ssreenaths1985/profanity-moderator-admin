package nxt.igot.vega.model;

public class Page {
	Integer number;
	Integer size;
	public Page() {
		super();
	}
	public Page(Integer number, Integer size) {
		super();
		this.number = number;
		this.size = size;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
