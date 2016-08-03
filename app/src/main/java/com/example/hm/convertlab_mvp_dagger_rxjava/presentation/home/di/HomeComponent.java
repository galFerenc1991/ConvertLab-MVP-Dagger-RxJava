package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.di;

import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.AppComponent;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.BankServiceModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.PerFragment;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.RetrofitModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragment;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragmentPresenterImpl;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.adapter.HomeAdapter;

import dagger.Component;

/**
 * Created by Ferenc on 2016.04.19..
 */
@Component(dependencies = {AppComponent.class},
        modules = {RetrofitModule.class, BankServiceModule.class, HomeModule.class})
@PerFragment
public interface HomeComponent {
    void inject(HomeFragment _homeFragment);

    void inject(HomeFragmentPresenterImpl _homeFragmentPresenterImpl);

    void inject(HomeAdapter _homeAdapter);


}
