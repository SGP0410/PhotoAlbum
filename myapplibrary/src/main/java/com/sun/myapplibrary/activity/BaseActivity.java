package com.sun.myapplibrary.activity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.sun.myapplibrary.R;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isNight;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNight();
        setContentView(layoutResID());
        initView();
        initColor(isNight);
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void isNight(){
        UiModeManager uiModeManager = (UiModeManager) getApplicationContext().getSystemService(Context.UI_MODE_SERVICE);
        changeStatusBarTextImgColor(uiModeManager.getNightMode()==UiModeManager.MODE_NIGHT_YES);
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected abstract int layoutResID();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initColor(boolean isNight);

    protected void toClass(Context context , Class<? extends BaseActivity> clazz , Bundle bundle){
        Intent intent = new Intent(context, clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void toClass(Context context , Class<? extends BaseActivity> clazz , Bundle bundle , int requestCode){
        Intent intent = new Intent(context, clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent , requestCode);
    }

    /******设置状态栏颜色******/
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeStatusBarTextImgColor(boolean color) {
        if (!color) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
            //设置状态栏背景颜色
            getWindow().setStatusBarColor(Color.parseColor("#FDFDFE"));
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            //设置状态栏背景颜色
            getWindow().setStatusBarColor(Color.parseColor("#111111"));
        }
        isNight = color;
    }
}
