package com.laomu.justgraduate.common.pagemapping;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.laomu.justgraduate.R;

import java.util.HashMap;

/**
 * Created by yipengmu on 2014/10/5.
 */
public class JGPageJumper {
    private static FragmentManager mFragmentManager;
    private static JGPageJumper ins;
    private static FragmentTransaction mFragmentTransaction;

    private HashMap<String, String> mPageNameMapping;

    public JGPageJumper(FragmentActivity act) {
        mFragmentManager = act.getSupportFragmentManager();
        mPageNameMapping = PageNamgeManeger.getPageNameMap();
    }

    public static JGPageJumper getInstance(FragmentActivity act) {
        if (ins == null) {
            ins = new JGPageJumper(act);
        }

        return ins;
    }

    public void openPage(String pageName, Boolean needAddToBackStack) {
        if (mFragmentManager == null) {
            return;
        }

        mFragmentTransaction = mFragmentManager.beginTransaction();
        //通过pagename 进行统一跳转拦截处理
        mFragmentTransaction.replace(R.id.container, getFragmentFromFragmentName(pageName), pageName);
        if (needAddToBackStack != null && needAddToBackStack) {
            mFragmentTransaction.addToBackStack(pageName);
        }
        mFragmentTransaction.commit();

    }


    public void openPageByActivity(Activity act, Class<?> actClass) {
        Intent intent = new Intent(act, actClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        act.startActivity(intent);
        act.overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
    }

    public void openPageByActivity(Activity context, Class<?> activity, int requestCode) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivityForResult(intent, requestCode);
    }

    public Fragment getFragmentFromFragmentName(String pageNmae) {
        if (mPageNameMapping == null) {
            return null;
        }

        String pageValue = mPageNameMapping.get(pageNmae);
        if (!TextUtils.isEmpty(pageNmae)) {
            try {
                return (Fragment) Class.forName(pageValue).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return null;
//        }
//
//            if ("justgraduate".equals(pageNmae)) {
//                return new JustGraduateFragment();
//            } else if ("gaokao".equals(pageNmae)) {
//                return new GaokaoFragment();
//            } else if ("rankingbrand".equals(pageNmae)) {
//                return new RankingBandFragment();
//            } else if ("groupself".equals(pageNmae)) {
//                return new GroupSelfFragment();
//            } else if ("settings".equals(pageNmae)) {
//                return new SettingFragment();
//            }
    }
}
