package com.sun.photoalbum;

import android.app.Application;

import com.sun.myapplibrary.ZhcsConfig;

public class PaApplication extends Application {
    private static int homeIndex = 1;
    @Override
    public void onCreate() {
        super.onCreate();
        ZhcsConfig.setContext(getApplicationContext());
        ZhcsConfig.setUrl("");
        ZhcsConfig.setToKen("");
    }

    public static int getHomeIndex() {
        return homeIndex;
    }

    public static void setHomeIndex(int homeIndex) {
        PaApplication.homeIndex = homeIndex;
    }
}
