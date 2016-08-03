package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.di;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.dao.BankDao;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepository;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepositoryImpl;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache.Cache;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache.CacheImpl;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.CloudDataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.DataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.LocalDataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.home.HomeInteractor;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.base.BaseInteractor;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.service_interactor.ServiceInteractor;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.PerFragment;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragmentContract;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragmentPresenterImpl;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ferenc on 2016.04.19..
 */
@Module
public class HomeModule {

    @Provides
    @PerFragment
    HomeFragmentContract.HomeFragmentPresenter provideHomeFragmentPresenter(final HomeFragmentPresenterImpl _presenter) {
        return _presenter;
    }

    @Provides
    @PerFragment
    BaseInteractor provideBaseInteractor(final HomeInteractor _homeInteractor){
        return _homeInteractor;
    }

}
