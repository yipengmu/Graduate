package com.laomu.justgraduate.http.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by yipengmu on 2014/11/25.
 */
public class NetUtil {
    public static Object convertJson2Obj(String jsondata, Class outClass) {
        if(TextUtils.isEmpty(jsondata) || outClass ==null){
            return null;
        }
        JSONObject jo = (JSONObject) JSONObject.parse(jsondata);
        Object out = null;
        try {

            out =  JSON.parseObject(jo.get("data").toString(), outClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }
}
