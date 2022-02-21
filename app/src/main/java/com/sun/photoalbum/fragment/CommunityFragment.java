package com.sun.photoalbum.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.sun.myapplibrary.fragment.BaseFragment;
import com.sun.photoalbum.R;

public class CommunityFragment extends BaseFragment {

    private LinearLayout linearLayout;

    @Override
    protected int layoutResId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initColor(boolean isNight) {
        if (isNight){
            linearLayout.setBackgroundColor(Color.parseColor("#242424"));
        }else {
            linearLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }
    }

    @Override
    public void onClick(View view) {

    }
}
