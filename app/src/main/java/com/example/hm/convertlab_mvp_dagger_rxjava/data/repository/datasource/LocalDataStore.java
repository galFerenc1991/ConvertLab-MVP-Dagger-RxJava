package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource;

import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.dao.BankDao;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by Ferenc on 2016.04.28..
 */
public class LocalDataStore implements DataStore {

    @Inject
    BankDao mBankDao;
    private BankResponse mBankResponse;

    @Inject
    public LocalDataStore(){}

    @Override
    public Observable<BankResponse> getBankList() {

        return Observable.create(new Observable.OnSubscribe<BankResponse>() {
            @Override
            public void call(Subscriber<? super BankResponse> subscriber) {
                try {
                    subscriber.onNext(getDataForDataBase());
                    subscriber.onCompleted();
                    Log.d("Local", "local");
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }

    public BankResponse getDataForDataBase(){
         mBankResponse = new BankResponse();
         mBankResponse.mBanks = mBankDao.getBank();
        return mBankResponse;
    }

}
