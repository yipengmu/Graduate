package com.laomu.justgraduate.modules.login.net;

import com.laomu.justgraduate.http.base.BaseRequest;

/**
 * Created by yipengmu on 2014/11/21.
 */
public class LoginRequest extends BaseRequest {
    public String uid;
    public String upassword;

    public LoginRequest() {
        super("login");
    }

    @Override
    public void addParams() {
        getParams().put("upassword",upassword);
        getParams().put("uid", uid);

    }
}
