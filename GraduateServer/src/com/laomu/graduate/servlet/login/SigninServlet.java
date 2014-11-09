package com.laomu.graduate.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.base.BaseHttpServlet;
import com.laomu.graduate.database.DBManeger;
import com.laomu.graduate.servlet.bean.UserBean;
import com.laomu.graduate.utils.CommonUtil;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = new UserBean();
		user.uid = CommonUtil.getStringParam(request, "uid");
		user.uname = CommonUtil.getStringParam(request, "uname");
		user.upassword = CommonUtil.getStringParam(request, "upassword");

		boolean bExist = DBManeger.getIns().selectUserByUserId(user.uid);
		if(!bExist){
			DBManeger.getIns().addUser(user);
			response.getWriter().print("{\"results\":\"success\"}");
		}else{
			response.getWriter().print("{\"results\":\"failed\"}");
		}
	}


}
