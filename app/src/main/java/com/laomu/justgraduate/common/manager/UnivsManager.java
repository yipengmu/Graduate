package com.laomu.justgraduate.common.manager;

import com.laomu.justgraduate.modules.login.account.UserInfo;

import java.util.List;

/**
 * Created by yipengmu on 2014/11/25.
 */
public class UnivsManager {

    private static UnivsManager ins = null;

    private UserInfo userBean;

    public List<UserInfo> getAllUserInfo() {
        return allUserInfo;
    }

    public void setAllUserInfo(List<UserInfo> allUserInfo) {
        this.allUserInfo = allUserInfo;
    }

    private List<UserInfo> allUserInfo;

    private UnivsManager() {
    }

    public static UnivsManager getInstance() {
        if (ins == null) {
            ins = new UnivsManager();
        }
        return ins;
    }

    public UserInfo getUserBean() {
        return userBean;
    }

    public void setUserBean(UserInfo userBean) {
        this.userBean = userBean;
    }

}
