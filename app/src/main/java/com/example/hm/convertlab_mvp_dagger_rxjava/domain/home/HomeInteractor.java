package com.example.hm.convertlab_mvp_dagger_rxjava.domain.home;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepository;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.base.BaseInteractor;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Ferenc on 2016.04.26..
 */
public class HomeInteractor extends BaseInteractor {

    private RetrofitRepository mRepository;

    @Inject
    public HomeInteractor(final RetrofitRepository _mRepository){
        mRepository = _mRepository;
    }


    @Override
    protected Observable buildGetObserver() {
        return mRepository.getBanks();
    }
}
