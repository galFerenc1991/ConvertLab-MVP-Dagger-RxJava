package com.example.hm.convertlab_mvp_dagger_rxjava.domain.base;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Ferenc on 2016.04.26..
 */
public abstract class BaseInteractor {

    private Subscription mGetSubscription = Subscriptions.empty();

    public BaseInteractor() {
    }

    public void executeGET(Observer _subscriber) {
        mGetSubscription = buildGetObserver()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_subscriber);
    }

//    public void unSubscribe() {
//        if (!mGetSubscription.isUnsubscribed())
//            mGetSubscription.unsubscribe();
//    }

    protected abstract Observable buildGetObserver();
}
