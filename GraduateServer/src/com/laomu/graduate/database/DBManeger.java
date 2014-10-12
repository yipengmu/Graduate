package com.laomu.graduate.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.laomu.graduate.bean.User;
import com.laomu.graduate.utils.CommonUtil;

public class DBManeger {

	private String url = "jdbc:mysql://localhost:3309/graduatedb";
	private String user = "root";
	private String password = "";
	private String driverClass = "com.mysql.jdbc.Driver";

	private Connection conn = null;
	/**
	 * 预处理对象
	 */
	private java.sql.PreparedStatement ps;

	private DBManeger() {
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			conn.createStatement().executeUpdate("use graduatedb");
		} catch (SQLException se) {
			System.out.println(driverClass + "数据库加载失败");
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(driverClass + "数据库加载失败");
			e.printStackTrace();
		}
	}

	public boolean addUser(User userInfo) {
		boolean result = false;
		if(userInfo == null || CommonUtil.isEmpty(userInfo._uid) || CommonUtil.isEmpty(userInfo._upassword) || CommonUtil.isEmpty(userInfo._uname)){
			return result;
		}
		try {

			String sql = "insert into userinfo (_uid,_upassword,_uname) values (?,?,?)";
			// sta =
			// conn.prepareStatement("insert into tb_name (col1,col2,col2,col4) values (null,null,null,null)");

			java.sql.PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, userInfo._uid);
			sta.setString(2, userInfo._upassword);
			sta.setString(3, userInfo._uname);
			sta.executeUpdate();

			// java.sql.PreparedStatement sta = conn.prepareStatement(sql);
			// sta.setString(1, userInfo._uid);
			// sta.setString(2, userInfo._uname);
			// sta.setString(2, userInfo._upassword);
			// conn.prepareStatement(sql);
			// conn.commit();
			// conn.createStatement().execute("insert into userinfo vlaues("
			// +user +")");
			result =  true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 执行插入语句,并返回生成的主键
	 * 
	 * @param sql
	 *            插入语句
	 * @param objs
	 *            参数列表
	 * @return 插入语句返回的主键值
	 * @throws SQLException
	 */
	public int insertAndReturnKey(String sql, Object... objs)
			throws SQLException {
		int countRow = 0;
		int key = 0;

		try {
			getConnection();

			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (objs != null) {
				for (int i = 0; i < objs.length; i++)
					ps.setObject(i + 1, objs[i]);
			}

			countRow = ps.executeUpdate();
			if (countRow > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					key = rs.getInt(1);
			}
			conn.commit();
		} catch (SQLException e) {
			countRow = 0;
			conn.rollback();
			close();
			throw e;
		} finally {
			if (conn != null) {
				conn.setAutoCommit(true);
			}
			close();
		}
		return key;
	}

	public void execSql(String sql) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("drop database if exists graduatedb");
			stmt.executeUpdate("create database graduatedb");
			stmt.executeUpdate("use graduatedb");
			stmt.executeUpdate(sql);
			stmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	private static DBManeger ins;

	public static DBManeger getIns() {
		if (ins == null) {
			ins = new DBManeger();
		}
		return ins;
	}

	public Connection getConnection() {
		return conn;
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			conn = null;
		}
	}
}
