package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.dao.BankDao;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache.CacheImpl;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource.DataStore;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.MyApplication;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.MyNetworkManagger;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Ferenc on 2016.04.26..
 */
public class RetrofitRepositoryImpl implements RetrofitRepository {
    private static final String TAG = "RetrofitRepositoryImpl";

    @Inject
    @Named("cloud")
    DataStore mCloudeDataStore;

    @Inject
    @Named("local")
    DataStore mLocalDataStore;

    @Inject
    Context mContext;

    @Inject
    CacheImpl mCacheImpl;

    @Inject
    BankDao mBankDao;

    @Inject
    public RetrofitRepositoryImpl(){
//        mCloudeDataStore = _cloudeDataStore;
//        mLocalDataStore = _localDataStore;
//        mContext = _context;
    }

    @Override
    public Observable<BankResponse> getBanks() {
        if (MyNetworkManagger.isNetworkAvailable(mContext)){
            return Observable.concat(mLocalDataStore.getBankList(), getCloud());
        } else {
            return mLocalDataStore.getBankList();
        }
    }

    @Override
    public Observable<BankResponse> getCloud() {
        return mCloudeDataStore.getBankList().doOnNext(new Action1<BankResponse>() {
            @Override
            public void call(BankResponse bankResponse) {
                mCacheImpl.cacheFill(bankResponse);
                Log.d(TAG, "call() called with: " + "bankResponse = [" + bankResponse + "]");
            }
        });
    }
}
