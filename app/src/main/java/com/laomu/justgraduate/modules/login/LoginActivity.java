package com.laomu.justgraduate.modules.login;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.laomu.justgraduate.R;
import com.laomu.justgraduate.base.BaseActivity;
import com.laomu.justgraduate.http.volley.VolloyManeger;
import com.laomu.justgraduate.utils.CommonUtils;
import com.laomu.justgraduate.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private Button btn_login;
    private Button btn_signin;
    private EditText et_user_id;
    private EditText et_user_password;

    private String userId;
    private String userPassword;
    private final String Tag = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        et_user_id = (EditText) findViewById(R.id.et_user_id);
        et_user_password = (EditText) findViewById(R.id.et_user_password);
        btn_signin.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                doLogin();
                break;
            case R.id.btn_signin:
                doSignin();
                break;
        }
    }

    private void doSignin() {
        userId = et_user_id.getText().toString();
        userPassword = et_user_password.getText().toString();
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("_uid", userId);
        params.put("_upassword", userPassword);
        params.put("_uname", "test");

        String url = "http://localhost:8080/GraduateServer/SigninServlet";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.d(Tag,response.toString());
            }

        },null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        VolloyManeger.getIns().query(stringRequest);
    }

    private void doLogin() {
        userId = et_user_id.getText().toString();
        userPassword = et_user_password.getText().toString();
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("_uid", userId);
        params.put("_upassword", userPassword);
        params.put("_uname", "test");

        String url = "http://localhost:8080/GraduateServer/LoginServlet";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jo = (JSONObject) JSONObject.parse(response);
                String result = jo.getString("results");
                if("success".equals(result)){
                    CommonUtils.toast("登录成功");
                    handleLogin(true);
                }else{
                    CommonUtils.toast("登录失败");
                    handleLogin(false);
                }
                LogUtils.d(Tag, response.toString());
            }

        },null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        VolloyManeger.getIns().query(stringRequest);
    }

    private void handleLogin(boolean isSucc) {
        if(isSucc){
            overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            finish();
        }
    }
}
