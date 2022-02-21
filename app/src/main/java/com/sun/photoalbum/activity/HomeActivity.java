package com.sun.photoalbum.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sun.myapplibrary.activity.BaseHomeActivity;
import com.sun.photoalbum.PaApplication;
import com.sun.photoalbum.R;
import com.sun.photoalbum.fragment.CenterFragment;
import com.sun.photoalbum.fragment.CommunityFragment;
import com.sun.photoalbum.fragment.PhotoAlbumFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends BaseHomeActivity {

    private BottomNavigationView nav;
    private Map<String, Fragment> fragmentMap;
    private FrameLayout frameLayout;

    @Override
    protected int layoutResID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        nav = (BottomNavigationView) findViewById(R.id.nav);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
    }

    @Override
    protected void initData() {
        initFragmentMap();
        initNav();
        showFragment(PaApplication.getHomeIndex());
    }

    public void showFragment(int index){
        nav.postDelayed(() -> {
            nav.setSelectedItemId(nav.getMenu().getItem(index).getItemId());
        } , 100);
    }

    private void initNav() {
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.community:
                        setFragment(Objects.requireNonNull(fragmentMap.get("社区")));
                        PaApplication.setHomeIndex(0);
                        break;
                    case R.id.photo_album:
                        setFragment(Objects.requireNonNull(fragmentMap.get("相册")));
                        PaApplication.setHomeIndex(1);
                        break;
                    case R.id.center:
                        setFragment(Objects.requireNonNull(fragmentMap.get("个人中心")));
                        PaApplication.setHomeIndex(2);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initColor(boolean isNight) {
        if (isNight){
            nav.setBackgroundColor(Color.parseColor("#111111"));
        }else {
            nav.setBackgroundColor(Color.parseColor("#FDFDFE"));
        }
    }

    /**
     * 初始化首页 Fragment
     */
    private void initFragmentMap() {
        fragmentMap = new HashMap<>();
        fragmentMap.put("社区" , new CommunityFragment());
        fragmentMap.put("相册" , new PhotoAlbumFragment());
        fragmentMap.put("个人中心" , new CenterFragment());
    }

    @Override
    protected int frameLayoutID() {
        return R.id.frame_layout;
    }

    @Override
    public void onClick(View view) {

    }
}