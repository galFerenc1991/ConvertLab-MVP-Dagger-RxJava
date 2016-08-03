package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di;


import android.content.Context;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepository;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.MyService;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.adapter.HomeAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ferenc on 2016.05.17..
 */

@Component(modules = {AppModule.class, RetrofitModule.class, BankServiceModule.class})
@Singleton
public interface AppComponent {
    Context context();
    RetrofitRepository repository();

    void inject(MyService _myService);
}
