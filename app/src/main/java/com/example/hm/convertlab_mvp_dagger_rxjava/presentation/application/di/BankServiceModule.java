package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit.BankService;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.PerFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Ferenc on 2016.04.19..
 */
@Module
public class BankServiceModule {

    @Provides
    @Singleton
    public BankService provideBankService(Retrofit _retrofit){
        return _retrofit.create(BankService.class);
    }
}
