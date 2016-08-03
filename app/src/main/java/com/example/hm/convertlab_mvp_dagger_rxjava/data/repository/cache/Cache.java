package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

/**
 * Created by Ferenc on 2016.05.12..
 */
public interface Cache {
    void cacheFill(BankResponse _bankResponce);
}
