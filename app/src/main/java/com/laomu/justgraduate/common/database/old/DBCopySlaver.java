package com.laomu.justgraduate.common.database.old;

import android.content.Context;

import com.laomu.justgraduate.application.JGApplication;
import com.laomu.justgraduate.preference.PreferenceManager;
import com.laomu.justgraduate.utils.CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by yipengmu on 2014/12/8.
 *
 * 用于客户端的 db 在 第一次 app 安装时 进行的 db　拷贝工作
 */
public class DBCopySlaver {
    private boolean workDone;
    private static Context mContext;
    private static String fileDBPath ;

    public static void init() {
        if (PreferenceManager.getDBFileCopyed()) {
            return;
        }


        mContext = JGApplication.appContext;
        fileDBPath = mContext.getDatabasePath(DatabaseHelper.DATABASE_NAME).getParentFile().getPath();
        try {
            boolean copySucc = CommonUtils.copyFile(fileDBPath,DatabaseHelper.DATABASE_NAME, mContext.getAssets().open(DatabaseHelper.DATABASE_NAME));

            if(copySucc){
                PreferenceManager.setDBFileCopyed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
