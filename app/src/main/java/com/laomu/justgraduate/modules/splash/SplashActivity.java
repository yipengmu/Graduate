package com.laomu.justgraduate.modules.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.laomu.justgraduate.HomeActivity;
import com.laomu.justgraduate.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity{

    public SplashActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer jumperTimer = new Timer();
        jumperTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                JumpHomeActivity();
            }
        },1500);
    }

    private void JumpHomeActivity() {
        startActivity(new Intent(this,HomeActivity.class));
        overridePendingTransition(R.anim.jg_alpha_in,R.anim.jg_alpha_out);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
}
