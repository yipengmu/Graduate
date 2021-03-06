package com.laomu.justgraduate.modules.homepage.tabbar;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.base.BaseFragment;
import com.laomu.justgraduate.modules.rank.ItemFragment;
import com.viewpagerindicator.TabPageIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RankingBandFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RankingBandFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RankingBandFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /**
     * Tab标题 工资水平，工作地点，所在行业，考研留学，结婚生子
     */
    private static final String[] TITLE = new String[] { "推荐","工资水平", "工作地点", "所在行业", "考研留学","结婚生子"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RankingBandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingBandFragment newInstance(String param1, String param2) {
        RankingBandFragment fragment = new RankingBandFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public RankingBandFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_ranking_band, container, false);

        initViewpager(mView);
        return  mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewpager(view);
    }

    private void initViewpager(View view) {
        //ViewPager的adapter
        TabPageIndicatorAdapter adapter = new TabPageIndicatorAdapter(getActivity().getSupportFragmentManager());
        ViewPager pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        //实例化TabPageIndicator然后设置ViewPager与之关联
        TabPageIndicator indicator = (TabPageIndicator)view.findViewById(R.id.ic_rank);
        indicator.setViewPager(pager);
        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                Toast.makeText(getActivity(), TITLE[arg0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    class TabPageIndicatorAdapter extends FragmentStatePagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //新建一个Fragment来展示ViewPager item的内容，并传递参数
            Fragment fragment = new ItemFragment();
            Bundle args = new Bundle();
            args.putString("arg", TITLE[position]);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

    }
}
