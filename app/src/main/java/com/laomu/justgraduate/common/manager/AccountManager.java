package com.laomu.justgraduate.common.manager;

import com.laomu.justgraduate.modules.login.account.Account;

import java.util.List;

/**
 * Created by yipengmu on 2014/11/25.
 */
public class AccountManager {

    private static AccountManager ins = null;

    private Account userBean;

    public List<Account> getAllAccount() {
        return allAccount;
    }

    public void setAllAccount(List<Account> allAccount) {
        this.allAccount = allAccount;
    }

    private List<Account> allAccount;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (ins == null) {
            ins = new AccountManager();
        }
        return ins;
    }

    public Account getUserBean() {
        return userBean;
    }

    public void setUserBean(Account userBean) {
        this.userBean = userBean;
    }

}
