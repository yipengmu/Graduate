package com.laomu.graduate.servlet.bean;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	  public List<UserBean> findUserById(String uid);  
	  public List<UserBean> findUserByIdAndPassword(Map<String, String> param);  
	  public boolean insertUser(UserBean user);  
	  public boolean updateUser(String uid,String upassword,String uname);  
	  public boolean delete(String uid);  
}
