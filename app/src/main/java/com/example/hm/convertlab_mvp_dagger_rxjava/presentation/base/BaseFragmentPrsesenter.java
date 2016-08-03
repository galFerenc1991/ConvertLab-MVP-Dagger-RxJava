package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.base;

/**
 * Created by Ferenc on 2016.04.27..
 */
public interface BaseFragmentPrsesenter<T> {
    void initUriForPhone(T _data);
    void initUriForMap(T _data);
    void initUriForLink(T _data);
}
