package com.laomu.graduate.common;

public class CommonDefine {
	public final static String SQL_CREATE_USERINFO = "create table userInfo(_id INT  primary key,_uid VARCHAR(50) not null,_upassword VARCHAR(50) not null ,_uname VARCHAR(50),_male VARCHAR(50) ,_face_logo VARCHAR(100) ,_tel VARCHAR(50) ,_signup_date DATE ,_school_name VARCHAR(50) ,_school_id VARCHAR(50) ,_department VARCHAR(50) ,_class VARCHAR(50) ,_grade VARCHAR(50) ,_degree VARCHAR(50) ,_last_login_time VARCHAR(50) )";
	public final static String API_PROVINCE_FIND_BY_NAME = "findbyname";
	public final static String API_PROVINCE_FIND_BY_ID = "findbyid";


	public final static String RET_START_SUCCESS = "{\"ret\":\"success\",";
	public final static String RET_STARTFAILED_FAILED = "{\"ret\":\"failed\",";
}
