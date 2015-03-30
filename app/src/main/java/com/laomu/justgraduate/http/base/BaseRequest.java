package com.laomu.justgraduate.http.base;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.laomu.justgraduate.application.CommonDefine;
import com.laomu.justgraduate.common.config.ENV;
import com.laomu.justgraduate.common.config.EnvConfig;
import com.laomu.justgraduate.http.net.JGMethod;
import com.laomu.justgraduate.http.volley.VolloyManeger;

import java.util.HashMap;
import java.util.Iterator;
import com.android.volley.Request.Method;
/**
 * Created by yipengmu on 2014/11/21.
 */
public class BaseRequest{

    /**
     * 默认get
     */
    private int methodType = Method.GET;
    private JGMethod requestType = JGMethod.HTTP;
    private String host = CommonDefine.LOCAL_IP;


    private String port = "80";


    private HashMap<String, String> params = new HashMap<String, String>();
    private Response.Listener listener  = null;

    public BaseRequest(String api) {
        setApi(api);
    }

    private String api = null;

    public BaseRequest(Request loginRequest, Class userBeanClass) {

    }

    public String getHost() {
        if (EnvConfig.env == ENV.REMOTE) {
            return CommonDefine.REMOTE_ONLINE_HOST;
        } else {
            return host;
        }
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        if("80".equals(port)){
            return "";
        }
        return ":";
    }

    public String getApp() {
        return "/GraduateServer";
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApi() {
        return "/" +api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    protected void addParams() {
    }


    public HashMap<String, String> getParams() {
        return params;
    }

    public String getParamsInfo() {
        if(params == null ||params.size() == 0){
            return "";
        }

        StringBuffer sb = new StringBuffer("?");
        Iterator<String> it = params.keySet().iterator();
        String key,value;
        while(it.hasNext()) {
            key = it.next();
            value = params.get(key);
            sb.append(key + "=" + value);
            sb.append("&");
        }

        String paramStr = sb.toString();
        return paramStr.substring(0,paramStr.lastIndexOf("&"));
    }


    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    private String getTargetRequstUrl() {
        StringBuffer sb = new StringBuffer();

        sb.append(getRequstType());
        sb.append(getHost());
        sb.append(getPort());
        sb.append(getApp());
        sb.append(getApi());
        sb.append(getParamsInfo());

        return sb.toString();
    }

    private String getRequstType() {
        if (requestType == JGMethod.HTTPS) {
            return "https://";
        }
        return "http://";
    }

    public void sendStringRequest(Response.Listener listener) {
        this.listener = listener;
        addParams();
        String urlString = getTargetRequstUrl();
        final StringRequest sr = new StringRequest(Request.Method.GET, urlString, listener, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                VolloyManeger.getIns().query(sr);
            }
        }).start();
    }


//    protected abstract String getUrl();
}
