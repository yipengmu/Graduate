package com.laomu.justgraduate.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.laomu.justgraduate.application.CommonDefine;
import com.laomu.justgraduate.application.JGApplication;

/**
 * Created by yipengmu on 2014/11/24.
 */
public class PreferenceManager {
    private static SharedPreferences sp = JGApplication.appContext.getSharedPreferences(CommonDefine.PREFS_FILE_NAME, Context.MODE_PRIVATE);
    private static SharedPreferences.Editor mPreferenceEdit = sp.edit();

    /**首页ShortCut是否显示过*/
    public static void setShortCutAdded() {
        mPreferenceEdit.putBoolean(CommonDefine.KEY_SHORT_CUT_EXISTS,true);
        mPreferenceEdit.commit();
    }

    /**首页ShortCut是否显示过*/
    public static boolean getShortCutIsAdded() {
        return sp.getBoolean(CommonDefine.KEY_SHORT_CUT_EXISTS,false);
    }


    /** 设置 app 的 db 是否已经从 assert copy 到 了 沙箱路径下：/data/data/packagename/databse/xx.db*/
    public static void setDBFileCopyed() {
        mPreferenceEdit.putBoolean(CommonDefine.KEY_DB_FILE_COPYED,true);
        mPreferenceEdit.apply();
    }

    /** 获取 app 的 db 是否已经从 assert copy 到 了 沙箱路径下：/data/data/packagename/databse/xx.db*/
    public static boolean getDBFileCopyed() {
        return sp.getBoolean(CommonDefine.KEY_DB_FILE_COPYED,false);
    }
}
