package com.laomu.justgraduate.common.pagemapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laomu.justgraduate.utils.CommonUtils;


import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by yipengmu on 2014/10/6.
 */
public class PageNamgeManeger {
    private static HashMap<String, String> pageNameMap = new HashMap<String, String>();

    public static void initPageNameMapping() {
        String pageJson = CommonUtils.getFromAssets("pagename.mapping");
        JSONObject jMap = JSON.parseObject(pageJson);

        JSONObject pageName = jMap.getJSONObject("pagenames");

        Iterator it = pageName.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = pageName.getString(key);
//            JSONArray array = pageName.getJSONArray(key);
            pageNameMap.put(key, value);
        }
    }


    public static HashMap<String, String> getPageNameMap() {
        return pageNameMap;
    }


}
