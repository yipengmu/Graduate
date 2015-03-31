package com.laomu.justgraduate.common.manager;

import com.laomu.justgraduate.modules.login.account.UserInfo;

import java.util.List;

/**
 * Created by yipengmu on 2014/11/25.
 */
public class AccountManager {

    private static AccountManager ins = null;

    private UserInfo userBean;

    public List<UserInfo> getAllUserInfo() {
        return allUserInfo;
    }

    public void setAllUserInfo(List<UserInfo> allUserInfo) {
        this.allUserInfo = allUserInfo;
    }

    private List<UserInfo> allUserInfo;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (ins == null) {
            ins = new AccountManager();
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
