package com.laomu.graduate.utils;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static String getStringParam(HttpServletRequest request, String paraName){
		return request.getParameter(paraName);
	}
	
	public static boolean isEmpty(String str){
		if(str == null || str.equals("")){
			return true;
		}else{
			return false;
		}
	}
}
