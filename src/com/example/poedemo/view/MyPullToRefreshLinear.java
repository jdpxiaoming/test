package com.example.poedemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class MyPullToRefreshLinear extends PullToRefreshBase<LinearLayout> {

	public MyPullToRefreshLinear(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyPullToRefreshLinear(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode, com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle animStyle) {
		super(context, mode, animStyle);
		// TODO Auto-generated constructor stub
	}

	public MyPullToRefreshLinear(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode) {
		super(context, mode);
		// TODO Auto-generated constructor stub
	}

	public MyPullToRefreshLinear(Context context) {
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
		LinearLayout linear = new LinearLayout(context,attrs);
		return linear;
	}

	@Override
	protected boolean isReadyForPullEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isReadyForPullStart() {
		// TODO Auto-generated method stub
		return true;
	}
}
