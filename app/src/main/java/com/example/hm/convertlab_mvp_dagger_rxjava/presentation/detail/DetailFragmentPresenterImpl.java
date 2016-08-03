package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit.BankService;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragmentContract;

import javax.inject.Inject;

/**
 * Created by Ferenc on 2016.04.21..
 */
public class DetailFragmentPresenterImpl implements DetailFragmentContract.DetailFragmentPresenter{
    DetailFragmentContract.DetailFragmentView view;

    @Inject
    public DetailFragmentPresenterImpl(){
    }

    @Override
    public void setView(DetailFragmentContract.DetailFragmentView _view) {
        view = _view;
    }

    @Override
    public void initUriForPhone(Banks _bank) {
        String uriForPhone = "tel:" + _bank.mPhoneNumber;
        view.touchPhone(uriForPhone);
    }

    @Override
    public void initUriForMap(Banks _bank) {
        String check = _bank.mAddress + "," + _bank.mCity + "," + _bank.mRegion;
        String map = "http://maps.google.co.in/maps?q=" + check;
        view.touchMap(map);
    }

    @Override
    public void initUriForLink(Banks _bank) {
        String uriForLink = _bank.mLink;
        view.touchLink(uriForLink);
    }


}
