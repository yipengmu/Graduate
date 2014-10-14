package com.laomu.justgraduate.http.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.laomu.justgraduate.application.JGApplication;

/**
 * Created by yipengmu on 2014/10/13.
 */
public class VolloyManeger {

    private static VolloyManeger ins = null;

    private RequestQueue requestQuee;
    public static VolloyManeger getIns() {
        if (ins == null) {
            ins = new VolloyManeger();
        }
        return ins;
    }

    private VolloyManeger() {
        requestQuee = Volley.newRequestQueue(JGApplication.appContext);
    }

    public void  query(Request request) {
        requestQuee.add(request);
    }


}
