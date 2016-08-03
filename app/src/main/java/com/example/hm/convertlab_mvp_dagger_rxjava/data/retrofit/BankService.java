package com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by hm on 13.04.2016.
 */
public interface BankService {
    String SERVICE_ENDPOINT = "http://resources.finance.ua";

    @GET("/ru/public/currency-cash.json")
    Observable<BankResponse> getBanks();
}
