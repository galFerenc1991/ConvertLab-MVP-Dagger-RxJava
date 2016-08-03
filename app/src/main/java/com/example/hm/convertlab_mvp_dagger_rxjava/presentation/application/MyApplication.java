package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application;

import android.app.Application;
import android.content.Context;

import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.AppComponent;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.AppModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.DaggerAppComponent;

/**
 * Created by Ferenc on 2016.05.05..
 */
public class MyApplication extends Application{
    public static boolean sIsActive = false;
    private static MyApplication sINSTANCE;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sINSTANCE = this;
        MyNotification.init(this);
        initializeInjector();
    }

    public void initializeInjector(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static MyApplication getInstance() {
        return sINSTANCE;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
