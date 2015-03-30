package com.laomu.justgraduate.modules.login.net;

import com.laomu.justgraduate.base.BaseNetBean;
import com.laomu.justgraduate.modules.login.account.Account;

/**
 * Created by yipengmu on 2014/11/27.
 */
public class UserBeanForNet extends BaseNetBean {

    private Account data;

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }
}
