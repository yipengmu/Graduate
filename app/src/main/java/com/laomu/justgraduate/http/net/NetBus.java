package com.laomu.justgraduate.http.net;

import com.android.volley.Request;
import com.laomu.justgraduate.http.base.BaseRequest;

/**
 * Created by yipengmu on 2014/11/25.
 */
public class NetBus<T> extends BaseRequest {

    public NetBus(String api) {
        super(api);
    }

    public NetBus(Request loginRequest, Class<T> userBeanClass) {
        super(loginRequest, userBeanClass);
    }
}
