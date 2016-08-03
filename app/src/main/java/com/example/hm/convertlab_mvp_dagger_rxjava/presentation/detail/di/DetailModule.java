package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.di;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.DetailFragmentContract;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.DetailFragmentPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ferenc on 2016.04.25..
 */
@Module
public class DetailModule {

    private Banks bank;
    public DetailModule(Banks _bank){
        bank = _bank;
    }

    @Provides
    @Singleton
    DetailFragmentContract.DetailFragmentPresenter provideHomeFragmentPresenter(final DetailFragmentPresenterImpl _presenter) {
        return _presenter;
    }

    @Provides
    @Singleton
    Banks provideBanks(){
        return bank;
    }
}
