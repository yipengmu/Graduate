package com.laomu.justgraduate.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.laomu.justgraduate.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    private android.app.ActionBar androidActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_jgbase);
        initAndoridActionBar();
    }

    public void initAndoridActionBar() {
        try {
            androidActionBar = getActionBar();
            if (androidActionBar != null) {
                androidActionBar.setDisplayShowTitleEnabled(true);
                androidActionBar.setDisplayHomeAsUpEnabled(true);
                androidActionBar.setHomeButtonEnabled(true);
                androidActionBar.setDisplayShowHomeEnabled(true);
                androidActionBar.setDisplayUseLogoEnabled(true);
                androidActionBar.setLogo(R.drawable.transparent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jgbase, menu);
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

    }

    @Override
    public void finish(){
        super.finish();
        this.overridePendingTransition(R.anim.push_in_left, R.anim.jg_slide_out_right);
    }

}
