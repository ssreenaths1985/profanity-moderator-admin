package nxt.igot.vega.model;

import nxt.igot.vega.util.Role;

public class UserDetail {
	String id;
	String password;
	Boolean isActive;
	Role role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UserDetail(String id, String password, Boolean isActive, Role role) {
		super();
		this.id = id;
		this.password = password;
		this.isActive = isActive;
		this.role = role;
	}
	
}
