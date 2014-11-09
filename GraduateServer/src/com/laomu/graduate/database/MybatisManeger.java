package com.laomu.graduate.database;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.laomu.graduate.servlet.bean.UserBean;
import com.laomu.graduate.servlet.bean.UserMapper;
import com.laomu.graduate.servlet.province.ProvinceBean;
import com.laomu.graduate.servlet.province.ProvinceBeanMapper;

public class MybatisManeger {

	/**
	 * 获得MyBatis SqlSessionFactory
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句
	 * ，commit，rollback，close等方法。
	 * 
	 * @return
	 */
	public static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	public static void main(String[] args) {

		SqlSession sqlSession = getSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<UserBean> user = userMapper.findUserById("111");
		System.out.println(user.get(0).uname);

		Map<String, String> param = new HashMap<String, String>();
		param.put("uid", "111");
		param.put("upassword", "333");
		List<UserBean> userWithPassword = userMapper.findUserByIdAndPassword(param);
		System.out.println(userWithPassword.get(0).uname);
		
		Map<String, String> paramProvince = new HashMap<String, String>();
		ProvinceBeanMapper provinceBeanMapper = sqlSession.getMapper(ProvinceBeanMapper.class);
//		try {
//			param.put("name", new String("\'北京\'".getBytes(),"utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} ok

		//直接使用string ,ok,但是因为中文字符串需要用引号标识出来，所以需要转义符+'
		param.put("name", "\'北京\'");
		ProvinceBean provinces = provinceBeanMapper.findProvinceByName(param);
//		ProvinceBean provinces = provinceBeanMapper.findProvinceById("1");
		if(provinces != null){
			System.out.println(provinces.name);
		}

	}
}
