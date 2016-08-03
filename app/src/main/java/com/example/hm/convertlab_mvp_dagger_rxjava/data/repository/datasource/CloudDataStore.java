package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.datasource;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit.BankService;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by Ferenc on 2016.04.28..
 */
public class CloudDataStore implements DataStore {

    private BankService service;

    @Inject
    public CloudDataStore(BankService _service){
        service = _service;
    }

    @Override
    public Observable<BankResponse> getBankList() {
        return service.getBanks();
    }
}
