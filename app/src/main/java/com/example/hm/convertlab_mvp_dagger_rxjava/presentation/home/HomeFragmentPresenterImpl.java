package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home;

import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.dao.BankDao;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.retrofit.BankService;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;
import com.example.hm.convertlab_mvp_dagger_rxjava.domain.base.BaseInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hm on 11.04.2016.
 */
public class HomeFragmentPresenterImpl implements HomeFragmentContract.HomeFragmentPresenter {
    private BankResponse bankResponse = new BankResponse();
    private BaseInteractor mHomeInteractor;
    private HomeFragmentContract.HomeFragmentView view;
    private List<Banks> mBanks = new ArrayList<>();

    @Inject
    public HomeFragmentPresenterImpl(final BaseInteractor _interactor) {
        mHomeInteractor = _interactor;

    }

    @Override
    public void loadBanks() {
        view.showProgressBar(true);
        if (mBanks.size() != 0){
            bankResponse.mBanks = mBanks;
            view.setBankList(bankResponse);
            view.showProgressBar(false);
        } else {
            mHomeInteractor.executeGET(new Subscriber<BankResponse>(){

                @Override
                public final void onCompleted() {

                    Log.d("Prog", "onComlette");
                }

                @Override
                public final void onError(Throwable e) {
                    Log.e("ConvertLab", e.getMessage());
                }

                @Override
                public final void onNext(BankResponse response) {
                    bankResponse = response;
                    Log.d("Life", "onNext");
                    view.setBankList(response);
                    mBanks = response.mBanks;
                    view.showProgressBar(false);
                }
            });
        }

    }

    @Override
    public void setView(HomeFragmentContract.HomeFragmentView _view) {
        view = _view;
    }

    @Override
    public void initUriForPhone(int _itemPosition) {
        String uriForPhone = "tel:" + bankResponse.mBanks.get(_itemPosition).mPhoneNumber;
        view.touchPhone(uriForPhone);
    }

    @Override
    public void initUriForMap(int _itemPosition) {
        String check = bankResponse.mBanks.get(_itemPosition).mAddress + "," + bankResponse.mBanks.get(_itemPosition).mCity + "," + bankResponse.mBanks.get(_itemPosition).mRegion;
                    String map = "http://maps.google.co.in/maps?q=" + check;
        view.touchMap(map);
    }

    @Override
    public void initUriForLink(int _itemPosition) {
        String uriForLink = bankResponse.mBanks.get(_itemPosition).mLink;
        view.touchLink(uriForLink);
    }

    @Override
    public void startDetailFragment(Banks _banks) {
        view.touchDetails(_banks);
    }
}

