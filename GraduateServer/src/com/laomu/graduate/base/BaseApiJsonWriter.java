package com.laomu.graduate.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.common.CommonDefine;
import com.laomu.graduate.utils.LogUtil;

public abstract class BaseApiJsonWriter {
	private Object outputObj = null;
	private String outputJsonInfo = null;
	private HttpServletResponse response;
	private PrintWriter printWriter;
	
	public <T>BaseApiJsonWriter(HttpServletResponse resp, Object output) {
		response = resp;
		outputObj = output;
		try {
			printWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public abstract String getOutputJsonData();

	public void publish() throws IOException{
		outputJsonInfo = getOutputJsonData();
		StringBuilder outStringBuilder = new StringBuilder();
		if (outputJsonInfo != null && printWriter != null) {
			LogUtil.debug("login servlet success");
			outStringBuilder.append(CommonDefine.RET_START_SUCCESS);
			outStringBuilder.append("\"data\":" + outputJsonInfo);
		} else {
			LogUtil.debug("login servlet failed");
			response.getWriter().print(CommonDefine.RET_STARTFAILED_FAILED);
		}
		outStringBuilder.append("}");
		
		LogUtil.debug("ProvinceBean handled = " + outStringBuilder.toString());
		printWriter.print(outStringBuilder.toString());
	}
}
