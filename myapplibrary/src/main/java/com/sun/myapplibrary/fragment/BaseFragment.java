package com.sun.myapplibrary.fragment;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.sun.myapplibrary.activity.BaseActivity;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private boolean isNight;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNight();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void isNight(){
        UiModeManager uiModeManager = (UiModeManager) getContext().getSystemService(Context.UI_MODE_SERVICE);
        isNight = (uiModeManager.getNightMode()==UiModeManager.MODE_NIGHT_YES);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutResId() , container , false);
    }

    protected abstract int layoutResId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initColor(isNight);
    }

    protected abstract void initView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

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
}
