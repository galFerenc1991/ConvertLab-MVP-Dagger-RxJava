package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.hm.convertlab_mvp_dagger_rxjava.R;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.global.Constants;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.MyApplication;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.DetailFragment;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.adapter.HomeAdapter;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.BankServiceModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.di.DaggerHomeComponent;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.di.HomeComponent;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.di.HomeModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.RetrofitModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hm on 11.04.2016.
 */
public class HomeFragment extends Fragment implements HomeFragmentContract.HomeFragmentView {

    @Inject
    HomeFragmentContract.HomeFragmentPresenter mHomeFragmentPresenter;

    @Inject
    HomeAdapter homeAdapter;

    HomeComponent mComponent;

    private View view;
    @Bind(R.id.home_recycler_view)
    RecyclerView mHomeResyclerView;
    @Bind(R.id.progBar)
    ProgressBar mProgBar;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Life", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        Log.d("Life", "onCreateView");
        getActivity().setTitle(Constants.HOME_TITLE);
        ButterKnife.bind(this, view);
        mHomeResyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mHomeResyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Life", "onActyvityCreated");
        inject();
        mHomeResyclerView.setAdapter(homeAdapter);
        mHomeFragmentPresenter.setView(this);
        mHomeFragmentPresenter.loadBanks();


    }

    private void inject() {
        if (mComponent == null)
            mComponent = DaggerHomeComponent.builder()
                    .appComponent(MyApplication.getInstance().getAppComponent())
                    .homeModule(new HomeModule())
                    .bankServiceModule(new BankServiceModule())
                    .retrofitModule(new RetrofitModule())
                    .build();

        if (mHomeFragmentPresenter == null)
            mComponent.inject(this);
    }

    @Override
    public void showProgressBar(Boolean _visibility) {
        if(_visibility){
            mProgBar.setVisibility(View.VISIBLE);
        } else {
            mProgBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void setBankList(BankResponse _bankResponse) {
        homeAdapter.setBankList(_bankResponse.mBanks);
    }

    @Override
    public void touchLink(String _uriForLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_uriForLink));
        getContext().startActivity(intent);
    }

    @Override
    public void touchMap(String _uriForMAp) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_uriForMAp));
        getContext().startActivity(intent);
    }

    @Override
    public void touchPhone(String _uriForPhone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(_uriForPhone));
        getContext().startActivity(intent);

    }

    @Override
    public void touchDetails(Banks _bank) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        DetailFragment dtFrag = DetailFragment.newInstance(_bank);
        ft.replace(R.id.fragCont, dtFrag)
                .addToBackStack(ft.getClass().getSimpleName())
                .commit();
    }
}
