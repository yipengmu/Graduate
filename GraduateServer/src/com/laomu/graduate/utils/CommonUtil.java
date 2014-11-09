package com.laomu.graduate.utils;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.common.CommonDefine;
import com.laomu.graduate.common.ErrorConstants;

public class CommonUtil {

	public static String getStringParam(HttpServletRequest request, String paraName) {
		try {
			return URLDecoder
					.decode(new String(request.getParameter(paraName).getBytes("ISO8859-1"), "UTF-8"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return request.getParameter(paraName);
		}

	}

	public static int getIntParam(HttpServletRequest request, String paraName) {
		try {
			return new Integer(request.getParameter(paraName));
		} catch (Exception e) {
			return -1;
		}

	}

	public static boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static void publishFailed(HttpServletResponse response, int errorCode) {
		try {
			response.getWriter().write(CommonDefine.RET_STARTFAILED_FAILED + getErrorInfo(errorCode) + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getErrorInfo(int errorCode) {
		String errorMsg = null;
		switch (errorCode) {
		case ErrorConstants.ERROR_CODE_FOR_INVALID_UNKNOW:
			errorMsg = ErrorConstants.ERROR_MSG_FOR_INVALID_UNKNOW;
			break;
		case ErrorConstants.ERROR_CODE_FOR_INVALID_PARAM:
			errorMsg = ErrorConstants.ERROR_MSG_FOR_INVALID_PARAM;
			break;
		case ErrorConstants.ERROR_CODE_FOR_API_NOT_FOUND:
			errorMsg = ErrorConstants.ERROR_MSG_FOR_API_NOT_FOUND;
			break;
		case ErrorConstants.ERROR_CODE_FOR_MEMORY:
			errorMsg = ErrorConstants.ERROR_MSG_FOR_MEMORY;
			break;
		case ErrorConstants.ERROR_CODE_FOR_SYSTEM:
			errorMsg = ErrorConstants.ERROR_MSG_FOR_SYSTEM;
			break;
		default:
			break;
		}
		return "\"error_msg\":\"" + errorMsg + "\"";
	}
}
