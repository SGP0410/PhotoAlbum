package com.sun.photoalbum.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sun.myapplibrary.adapter.BaseFragmentPagerAdapter;
import com.sun.myapplibrary.fragment.BaseFragment;
import com.sun.photoalbum.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoAlbumFragment extends BaseFragment {

    private LinearLayout linearLayout;
    private ImageView search;
    private TextView pic;
    private TextView classify;
    private ImageView menu;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected int layoutResId() {
        return R.layout.fragment_photo_album;
    }

    @Override
    protected void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        search = (ImageView) view.findViewById(R.id.search);
        pic = (TextView) view.findViewById(R.id.pic);
        classify = (TextView) view.findViewById(R.id.classify);
        menu = (ImageView) view.findViewById(R.id.menu);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    }

    @Override
    protected void initData() {
        setFragmentList();
        showViewPager();
        initOnClick();
    }



    private void setFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new PicFragment());
        fragmentList.add(new ClassifyFragment());
    }

    private void showViewPager() {
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new BaseFragmentPagerAdapter(Objects.requireNonNull(getFragmentManager()), fragmentList) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return super.getItem(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        pic.setTextColor(Color.parseColor("#0175EE"));
                        classify.setTextColor(Color.parseColor("#333333"));
                        break;
                    case 1:
                        pic.setTextColor(Color.parseColor("#333333"));
                        classify.setTextColor(Color.parseColor("#0175EE"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initColor(boolean isNight) {
        if (isNight) {
            linearLayout.setBackgroundColor(Color.parseColor("#242424"));
        } else {
            linearLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }
    }

    private void initOnClick() {
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
