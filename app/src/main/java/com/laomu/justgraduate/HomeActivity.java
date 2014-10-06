package com.laomu.justgraduate;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.laomu.justgraduate.common.CommonDefine;
import com.laomu.justgraduate.common.custormviews.JGTabbarView;
import com.laomu.justgraduate.common.pagemapping.JGPageJumper;
import com.laomu.justgraduate.modules.hotactions.JGHotActionsFragment;
import com.laomu.justgraduate.modules.tabbar.JustGraduateFragment;
import com.laomu.justgraduate.modules.tabbar.RankingBandFragment;


public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, JGHotActionsFragment.OnFragmentInteractionListener, RankingBandFragment.OnFragmentInteractionListener, JGTabbarView.OnTabSelectedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private JGTabbarView mTabbarViewGroup;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private long mFirstime;
    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFragmentManager = getSupportFragmentManager();

        initViews();
        initTabbar();
    }

    private void initTabbar() {
        mTabbarViewGroup = (JGTabbarView) findViewById(R.id.home_tool_bar);
        mTabbarViewGroup.setOnTabSelectedListener(this);
    }

    private void initViews() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                mFragmentManager.findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //show item 1
        mFragmentManager.beginTransaction()
                .add(R.id.container, new JustGraduateFragment())
                .commit();

    }

    @Override
    public void onNavigationDrawerItemSelected(String pagename) {
        // update the main content by replacing fragments
        mFragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        mTitle = CommonDefine.DRAWER_STRING_LIST_CONTENT[number - 1];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
    public void onBackPressed() {
        long secondtime = System.currentTimeMillis();
        if (secondtime - mFirstime > 3000) {
            Toast.makeText(HomeActivity.this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            mFirstime = System.currentTimeMillis();
            return ;
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTabSelected(int index) {
        JGPageJumper.getInstance(this).openPage(CommonDefine.TABBAR_FRAGMENT_PAGES[index], false);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        static int posIndex = -1;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            posIndex = sectionNumber;
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            TextView tvLabel = (TextView) rootView.findViewById(R.id.section_label);
            tvLabel.setText(CommonDefine.DRAWER_STRING_LIST_CONTENT[posIndex] + "");
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }


    }

}
