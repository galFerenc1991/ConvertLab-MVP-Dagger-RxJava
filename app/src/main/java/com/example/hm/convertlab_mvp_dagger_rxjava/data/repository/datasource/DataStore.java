package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import rx.Observable;

/**
 * Created by Ferenc on 2016.04.28..
 */
public interface DataStore {

    Observable<BankResponse> getBankList();
}
