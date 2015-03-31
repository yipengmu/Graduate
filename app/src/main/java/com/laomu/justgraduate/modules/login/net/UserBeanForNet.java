package com.laomu.justgraduate.modules.login.net;

import com.laomu.justgraduate.base.BaseNetBean;
import com.laomu.justgraduate.modules.login.account.UserInfo;

/**
 * Created by yipengmu on 2014/11/27.
 */
public class UserBeanForNet extends BaseNetBean {

    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
