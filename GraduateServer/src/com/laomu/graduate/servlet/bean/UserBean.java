package com.laomu.graduate.servlet.bean;

public class UserBean {

	public String id;
	public String uid;
	public String upassword;
	public String uname;
	public String male;
	public String facelogo;
	public String tel;
	public String signupdate;
	public String schoolname;
	public String schoolid;
	public String department;
	public String classes;
	public String grade;
	public String degree;

	@Override
	public String toString() {
		return "uid=" + uid + "  uname=" + uname + " upassword=" + upassword; 
	}
}
