package nxt.igot.vega.model;

public class Status<T> {
	String status;
	Integer code;
	T payload;
	public Status() {
		super();
	}
	public Status(String status, Integer code, T payload) {
		super();
		this.status = status;
		this.code = code;
		this.payload = payload;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}

}
