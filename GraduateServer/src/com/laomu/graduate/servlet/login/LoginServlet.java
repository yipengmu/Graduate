package com.laomu.graduate.servlet.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.base.BaseHttpServlet;
import com.laomu.graduate.database.DBManeger;
import com.laomu.graduate.database.MybatisManeger;
import com.laomu.graduate.servlet.bean.UserBean;
import com.laomu.graduate.servlet.bean.UserMapper;
import com.laomu.graduate.servlet.province.ProvinceBean;
import com.laomu.graduate.servlet.province.ProvinceBeanMapper;
import com.laomu.graduate.utils.CommonUtil;
import com.laomu.graduate.utils.LogUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see BaseHttpServlet#BaseHttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserBean user = new UserBean();

		user.uid = CommonUtil.getStringParam(request, "uid");
		user.uname = CommonUtil.getStringParam(request, "uname");
		user.upassword = CommonUtil.getStringParam(request, "upassword");
		
		UserMapper userMapper = MybatisManeger.getSessionFactory().openSession().getMapper(UserMapper.class);
		List<UserBean> userBeanList = userMapper.findUserById(user.uid);
		
		
		if (userBeanList != null && userBeanList.size()>0) {
			response.getWriter().print("{\"results\":\"success\"}");
			LogUtil.warn("login servlet success");
			System.out.println(userBeanList.get(0).toString());
		} else {
			response.getWriter().print("{\"results\":\"failed\"}");
			LogUtil.warn("login servlet failed");
			System.out.println(userBeanList.toString());
			
		}
	}

}
