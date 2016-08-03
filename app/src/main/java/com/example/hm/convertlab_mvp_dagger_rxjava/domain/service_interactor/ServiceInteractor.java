package com.example.hm.convertlab_mvp_dagger_rxjava.domain.service_interactor;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.RetrofitRepository;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.base.BaseInteractor;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Ferenc on 2016.05.20..
 */
public class ServiceInteractor extends BaseInteractor {
    private RetrofitRepository mRetrofitRepository;

    @Inject
    public ServiceInteractor(final RetrofitRepository _retrofitRepositori){
        mRetrofitRepository = _retrofitRepositori;
    }

    @Override
    protected Observable buildGetObserver() {
        return mRetrofitRepository.getCloud();
    }
}
