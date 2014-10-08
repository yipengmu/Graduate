package com.laomu.justgraduate.application;

import android.os.AsyncTask;

import com.laomu.justgraduate.common.pagemapping.PageNamgeManeger;

/**
 * Created by yipengmu on 2014/10/6.
 */
public class ApplicationAsyncTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        PageNamgeManeger.initPageNameMapping();
        return null;
    }
}
