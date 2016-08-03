package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import rx.Observable;

/**
 * Created by Ferenc on 2016.04.26..
 */
public interface RetrofitRepository {
    Observable<BankResponse> getBanks();
    Observable<BankResponse> getCloud();
}
