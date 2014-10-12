package com.laomu.graduate.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.base.BaseHttpServlet;
import com.laomu.graduate.bean.User;
import com.laomu.graduate.common.CommonDefine;
import com.laomu.graduate.database.DBManeger;
import com.laomu.graduate.utils.CommonUtil;

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
		PrintWriter out = response.getWriter();
		User user = new User();
		
		user._uid = CommonUtil.getStringParam(request,"_uid");
		user._uname = CommonUtil.getStringParam(request,"_uname");
		user._upassword = CommonUtil.getStringParam(request,"_upassword");
		
//		user._uid = "uid111";
//		user._uname = "uname222";
//		user._upassword = "upasword333";
		
		boolean insertResult = DBManeger.getIns().addUser(user);
//		execSql(CommonDefine.SQL_CREATE_USERINFO);
		if(insertResult){
			out.println("login servlet success");
		}else{
			out.println("login servlet failed");
		}
	}

}
