package com.laomu.justgraduate.common.pagemapping;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.modules.tabbar.GaokaoFragment;
import com.laomu.justgraduate.modules.tabbar.GroupSelfFragment;
import com.laomu.justgraduate.modules.tabbar.JustGraduateFragment;
import com.laomu.justgraduate.modules.tabbar.RankingBandFragment;

/**
 * Created by yipengmu on 2014/10/5.
 */
public class JGPageJumper {
    private static FragmentManager mFragmentManager;
    private static JGPageJumper ins;
    private static FragmentTransaction mFragmentTransaction;

    public JGPageJumper(FragmentActivity act) {
        mFragmentManager = act.getSupportFragmentManager();
    }

    public static JGPageJumper getInstance(FragmentActivity act) {
        if (ins == null) {
            ins = new JGPageJumper(act);
        }

        return ins;
    }

    public void openPage(String pageName,Boolean needAddToBackStack) {
        if(mFragmentManager == null) {
            return;
        }

        boolean defaultTrue = true;
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.replace(R.id.container, getFragmentFromFragmentName(pageName),pageName);
        if(needAddToBackStack != null && needAddToBackStack) {
            mFragmentTransaction.addToBackStack(pageName);
        }
        mFragmentTransaction.commit();

    }

    public Fragment getFragmentFromFragmentName(String pageNmae) {
        if("justgraduate".equals(pageNmae)){
            return new JustGraduateFragment();
        }else   if("gaokao".equals(pageNmae)){
            return new GaokaoFragment();
        }else   if("rankingbrand".equals(pageNmae)){
            return new RankingBandFragment();
        }else   if("groupself".equals(pageNmae)){
            return new GroupSelfFragment();
        }
        return null;
    }
}
