package com.laomu.justgraduate.modules.login.net;

import com.laomu.justgraduate.http.base.BaseRequest;
import com.laomu.justgraduate.modules.login.account.Account;

/**
 * Created by yipengmu on 2014/11/21.
 */
public class LoginRequest extends BaseRequest {
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public LoginRequest() {
        super("AccountServlet");
    }

    @Override
    public void addParams() {
        getParams().put("api","findbyuid");

        getParams().put("uid", getUid());

    }
}
