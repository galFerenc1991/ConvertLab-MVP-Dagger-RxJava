package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di;

import android.app.Application;
import android.content.Context;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepository;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepositoryImpl;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache.Cache;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache.CacheImpl;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.CloudDataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.DataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.LocalDataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.base.BaseInteractor;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.service_interactor.ServiceInteractor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ferenc on 2016.05.17..
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application _application){
        mApplication = _application;
    }

    @Provides
    @Singleton
    Context provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    BaseInteractor provideBaseInteractor(final ServiceInteractor _serviceInteractor){
        return _serviceInteractor;
    }

    @Provides
    @Singleton
    RetrofitRepository provideRetrofitRepository(final RetrofitRepositoryImpl _retrofitRepositoryImpl){
        return _retrofitRepositoryImpl;
    }

    @Provides
    @Singleton
    @Named("cloud")
    DataStore provideCloudDataStore(final CloudDataStore _cloudDatastore){
        return _cloudDatastore;
    }

    @Provides
    @Singleton
    @Named("local")
    DataStore provideLocalDataStore(final LocalDataStore _localDatastore){
        return _localDatastore;
    }

    @Provides
    @Singleton
    Executor provideExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    Cache provideCacheImpl(CacheImpl _cache){
        return _cache;
    }

}
