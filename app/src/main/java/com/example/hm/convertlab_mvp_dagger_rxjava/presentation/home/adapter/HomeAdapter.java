package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.hm.convertlab_mvp_dagger_rxjava.R;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.di.PerFragment;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragmentContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by hm on 14.04.2016.
 */
@PerFragment
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<Banks> mBanksList;
    HomeFragmentContract.HomeFragmentPresenter homeFragmentPresenter;

    @Inject
    public HomeAdapter(HomeFragmentContract.HomeFragmentPresenter _homeFragmentPresenter) {
        mBanksList = new ArrayList<>();
        homeFragmentPresenter = _homeFragmentPresenter;

    }

    public void setBankList(List<Banks> _bankList) {
        mBanksList = _bankList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card_view, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Banks bank = mBanksList.get(position);
        holder.mBankName.setText(bank.mBankName);
        holder.mRegionName.setText(bank.mRegion);
        holder.mCityName.setText(bank.mCity);
        holder.mAddress.setText(bank.mAddress);
        holder.mPhoneNumber.setText(bank.mPhoneNumber);
        holder.ivCall.setOnClickListener(holder);
        holder.ivCall.setTag(position);
        holder.ivLink.setOnClickListener(holder);
        holder.ivLink.setTag(position);
        holder.ivMap.setOnClickListener(holder);
        holder.ivMap.setTag(position);
        holder.ivNext.setOnClickListener(holder);
        holder.ivNext.setTag(position);
        holder.cardView.setOnClickListener(holder);
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mBanksList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mBankName;
        public TextView mRegionName;
        public TextView mCityName;
        public TextView mPhoneNumber;
        public TextView mAddress;
        public ImageView ivCall;
        public ImageView ivMap;
        public ImageView ivLink;
        public ImageView ivNext;
        public CardView cardView;


        public MyViewHolder(View v) {
            super(v);
            mBankName = (TextView) v.findViewById(R.id.bankName);
            mRegionName = (TextView) v.findViewById(R.id.regionName);
            mCityName = (TextView) v.findViewById(R.id.cityName);
            mAddress = (TextView) v.findViewById(R.id.adress);
            mPhoneNumber = (TextView) v.findViewById(R.id.phoneNumber);
            ivCall = (ImageView) v.findViewById(R.id.btnPhone);
            ivLink = (ImageView) v.findViewById(R.id.btnLink);
            ivMap = (ImageView) v.findViewById(R.id.btnMap);
            ivNext = (ImageView) v.findViewById(R.id.btnNext);
            cardView = (CardView) v.findViewById(R.id.card_view);

        }

        @Override
        public void onClick(View v) {
            int _position = (int) v.getTag();
            Banks bank = mBanksList.get(_position);

            switch (v.getId()) {
                case R.id.btnPhone:
                    homeFragmentPresenter.initUriForPhone(_position);
                    break;
                case R.id.btnLink:
                    homeFragmentPresenter.initUriForLink(_position);
                    break;
                case R.id.btnMap:
                    homeFragmentPresenter.initUriForMap(_position);
                    break;
                case R.id.btnNext:
                case R.id.card_view:
                    homeFragmentPresenter.startDetailFragment(bank);
                    break;
            }
        }
    }
}
