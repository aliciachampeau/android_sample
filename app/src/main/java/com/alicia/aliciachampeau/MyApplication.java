package com.alicia.aliciachampeau;

import android.app.Application;

/**
 * Created by suiliang on 2/17/18.
 */

public final class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (MyApplication.class) {
            instance = this;
        }
        AlphaAdvantageApi.init(this);
    }

    public static synchronized Application getInstance() {
        return instance;
    }
}
