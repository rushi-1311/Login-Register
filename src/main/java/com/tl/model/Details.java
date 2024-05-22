package com.tl.model;
import com.tl.register.*;
public class Details {

	private String name;
	private String email;
	private String uname;
	private String pword;
	private String gender;
	public Details() {
		super();
		
	}
	public Details(String name, String email, String uname, String pword, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.uname = uname;
		this.pword = pword;
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getUname() {
		return uname;
	}
	public String getPword() {
		return pword;
	}
	public String getGender() {
		return gender;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Details [name=" + name + ", email=" + email + ", uname=" + uname + ", pword=" + pword + ", gender="
				+ gender + "]";
	}
	
}
