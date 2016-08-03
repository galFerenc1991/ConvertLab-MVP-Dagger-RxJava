package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.domain.base.BaseInteractor;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.AppModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.DaggerAppComponent;
import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Ferenc on 2016.05.16..
 */
public class MyService extends IntentService {

    @Inject
    BaseInteractor mServiceInteractor;

    public MyService() {
        super("MyService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (MyApplication.sIsActive)
            return;
        Log.d("Ricsi", "frissites");

        if (MyNetworkManagger.isNetworkAvailable(this)){
            startRefreshDatabase();
        }
    }

    public void startRefreshDatabase(){
        mServiceInteractor.executeGET(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }

    private void inject() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(MyApplication.getInstance()))
                .build()
                .inject(this);
    }

}