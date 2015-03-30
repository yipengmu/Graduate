package com.laomu.justgraduate.modules.login;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.common.database.OrmDbManager;
import com.laomu.justgraduate.common.database.old.DBManager;
import com.laomu.justgraduate.common.datatype.Province;
import com.laomu.justgraduate.common.datatype.School;
import com.laomu.justgraduate.common.datatype.Univ;
import com.laomu.justgraduate.common.manager.AccountManager;
import com.laomu.justgraduate.modules.login.account.Account;
import com.laomu.justgraduate.modules.login.adapter.SigninListviewAdapter;

import java.util.List;

public class SigninActivity extends ActionBarActivity {

    private ListView listView;
    private SigninListviewAdapter mAdapter;

    private List<Province> provincesList;
    private List<School> schoolsList;
    private List<Univ> univsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jg_activity_signin);


        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_content);

        mAdapter = new SigninListviewAdapter();
        getDBdatasource();
        mAdapter.setDataSource(provincesList);
        listView.setAdapter(mAdapter);
    }

    private void getDBdatasource() {
        provincesList = OrmDbManager.getInstance().getAllProvices();
        schoolsList = OrmDbManager.getInstance().getAllSchools();
        univsList = OrmDbManager.getInstance().getAllUniv();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.signin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
