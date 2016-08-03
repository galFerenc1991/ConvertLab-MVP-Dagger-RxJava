package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit.BankService;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit.MyJsonDeserializer;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.PerFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ferenc on 2016.04.19..
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson _gson){
        return new Retrofit.Builder()
                .baseUrl(BankService.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(_gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build())
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson(MyJsonDeserializer _deserialiser){
        return new GsonBuilder()
                .registerTypeAdapter(BankResponse.class, _deserialiser)
                .create();
    }
}
