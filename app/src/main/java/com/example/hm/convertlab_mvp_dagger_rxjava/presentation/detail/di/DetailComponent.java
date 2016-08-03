package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.di;

import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.DetailFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ferenc on 2016.04.25..
 */
@Component(modules = {DetailModule.class})
@Singleton
public interface DetailComponent {
    void inject(DetailFragment _detailFragment);
}
