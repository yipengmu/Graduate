package com.laomu.justgraduate.modules.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laomu.justgraduate.R;
import com.laomu.justgraduate.application.CommonDefine;
import com.laomu.justgraduate.base.BaseFragment;
import com.laomu.justgraduate.utils.CommonUtils;

public class SettingFragment extends BaseFragment implements OnClickListener {

	private static final String TAG = "SettingFragment";
	private Activity mActivity;
	private TextView mTitleTv;
	private RelativeLayout mShareFriendLayout;
	private RelativeLayout mFeedbackLayout;
	private RelativeLayout mAboutUsLayout;
	private RelativeLayout mAppRecommendLayout;
	private RelativeLayout mClearCacheLayout;

	public static SettingFragment newInstance() {
		SettingFragment settingFragment = new SettingFragment();

		return settingFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, container,
				false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
		initEvents();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initViews(View view) {

		mShareFriendLayout = (RelativeLayout) view.findViewById(R.id.rl_share_to_friend_layout);
		mFeedbackLayout = (RelativeLayout) view.findViewById(R.id.feedback_layout);
		mAboutUsLayout = (RelativeLayout) view.findViewById(R.id.about_us_layout);
		mAppRecommendLayout = (RelativeLayout) view.findViewById(R.id.app_recommend_layout);
		mClearCacheLayout = (RelativeLayout) view.findViewById(R.id.clear_cache_layout);
	}
	
	private void initEvents() {
		mShareFriendLayout.setOnClickListener(this);
		mFeedbackLayout.setOnClickListener(this);
		mAboutUsLayout.setOnClickListener(this);
		mAppRecommendLayout.setOnClickListener(this);
		mClearCacheLayout.setOnClickListener(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_share_to_friend_layout:
            getActivity().startActivityForResult(CommonUtils.getShareIntent(getActivity(), getShareText(), CommonDefine.SNS_SHARE_BUNDLE_MSG),0);
            break;
		case R.id.feedback_layout:
			break;
		case R.id.about_us_layout:
			break;
		case R.id.app_recommend_layout:
			break;
		case R.id.clear_cache_layout:
			break;

		default:
			break;
		}
	}

    private String getShareText() {
        return "分享分享分享-内容内容";
    }

}
