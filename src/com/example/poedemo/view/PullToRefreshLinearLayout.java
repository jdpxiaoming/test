package com.example.poedemo.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

public class PullToRefreshLinearLayout extends PullToRefreshBase<LinearLayout> {

	public PullToRefreshLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public PullToRefreshLinearLayout(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode, com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle animStyle) {
		super(context, mode, animStyle);
		// TODO Auto-generated constructor stub
	}

	public PullToRefreshLinearLayout(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode) {
		super(context, mode);
		// TODO Auto-generated constructor stub
	}

	public PullToRefreshLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
		// TODO Auto-generated method stub
		return Orientation.VERTICAL;
	}

	@Override
	protected LinearLayout createRefreshableView(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		LinearLayout linearLayout;
		if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
			linearLayout = new LinearLayout(context, attrs);
		} else {
			linearLayout = new LinearLayout(context, attrs);
		}

		linearLayout.setId(R.id.linearlayout);
		return linearLayout;
	}

	@Override
	protected boolean isReadyForPullEnd() {
		// TODO Auto-generated method stub
		LinearLayout refreshableView = getRefreshableView();

		if (null != refreshableView) {
			return refreshableView.getScrollY() == 0;
		}

		return false;
	}

	@Override
	protected boolean isReadyForPullStart() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
