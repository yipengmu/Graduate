package com.laomu.graduate.servlet.shcool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laomu.graduate.base.BaseHttpServlet;
import com.laomu.graduate.utils.CommonUtil;

/**
 * Servlet implementation class SchoolServlet
 */
@WebServlet("/SchoolServlet")
public class SchoolServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		user._uid = CommonUtil.getStringParam(request, "_uid");
//		user._uname = CommonUtil.getStringParam(request, "_uname");
//		user._upassword = CommonUtil.getStringParam(request, "_upassword");

	}

}
