package com.laomu.graduate.servlet.province;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.base.BaseHttpServlet;
import com.laomu.graduate.common.CommonDefine;
import com.laomu.graduate.common.ErrorConstants;
import com.laomu.graduate.database.MybatisManeger;
import com.laomu.graduate.utils.CommonUtil;
import com.laomu.graduate.utils.LogUtil;

@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	private ProvinceBeanMapper provinceBeanMapper = MybatisManeger.getSessionFactory().openSession()
			.getMapper(ProvinceBeanMapper.class);
	
	/**
	 * @see BaseHttpServlet#BaseHttpServlet()
	 */
	public ProvinceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProvinceBean pBean = new ProvinceBean();
		pBean.id = CommonUtil.getIntParam(request, "id");
		pBean.name = CommonUtil.getStringParam(request, "name");
		LogUtil.debug("ProvinceBean=" + pBean.toString());

		String api = CommonUtil.getStringParam(request, "api");
		
		int paramCheckState = checkParams(pBean,api);
		
		if(paramCheckState < 0){
			CommonUtil.publishFailed(response,paramCheckState);
			return;
		}
		
		if (CommonDefine.API_PROVINCE_FIND_BY_NAME.equals(api)) {
			ProvinceBean proResult = execProviceApiFindByName(pBean);
			new ProvinceApiJsonWriter(response,proResult).publish();
			
		} else if (CommonDefine.API_PROVINCE_FIND_BY_ID.equals(api)){
			ProvinceBean proResult = execProviceApiFindById(pBean);
			new ProvinceApiJsonWriter(response,proResult).publish();
		}else{
			CommonUtil.publishFailed(response,ErrorConstants.ERROR_CODE_FOR_INVALID_UNKNOW);
		}

	}

	private int checkParams(ProvinceBean pBean, String api) {
		if(pBean.id <= 0 || CommonUtil.isEmpty(pBean.name)){
			return ErrorConstants.ERROR_CODE_FOR_INVALID_PARAM;
		}else if(!CommonDefine.API_PROVINCE_FIND_BY_ID.equals(api) && !CommonDefine.API_PROVINCE_FIND_BY_NAME.equals(api)){
			return ErrorConstants.ERROR_CODE_FOR_API_NOT_FOUND;
		}
		return 0;
	}

	private ProvinceBean execProviceApiFindByName(ProvinceBean pBean) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", "\'" + pBean.name + "\'");
		return provinceBeanMapper.findProvinceByName(paramMap);
	}
	
	private ProvinceBean execProviceApiFindById(ProvinceBean pBean) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", String.valueOf(pBean.id));
		return provinceBeanMapper.findProvinceById(paramMap);
	}


}
