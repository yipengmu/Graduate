package com.laomu.justgraduate.application;

import android.app.Application;
import android.content.Context;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.utils.LogUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by yipengmu on 2014/10/6.
 */
public class JGApplication extends Application {
    public static Context appContext;
    String Tag = "just graduate";
    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;

        setUpTask();
        LogUtils.d(Tag,"application oncreate .....");

    }

    private void setUpTask() {
        new ApplicationAsyncTask().execute();
        initGlobleFontTypeFace();
    }

    private void initGlobleFontTypeFace() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/hyxzyj.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }


}
