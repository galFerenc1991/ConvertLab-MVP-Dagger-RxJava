package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hm.convertlab_mvp_dagger_rxjava.R;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.global.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ferenc on 2016.04.21..
 */
@Singleton
public class DetailAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Banks mBank;

    @Inject
    public DetailAdapter(Banks _bank){
        mBank = _bank;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 0){
            type = Constants.HEADER_TYPE;
        }else type = Constants.CURRENCIES_TYPE;
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == Constants.HEADER_TYPE) {
            View headerItem = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_card_view, parent, false);

            return new HeaderViewHolder(headerItem);
        } else {

            View currenciesItem = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.currencies_card_view, parent, false);
            return new CurrenciesViewHolder(currenciesItem);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            HeaderViewHolder HVH = (HeaderViewHolder)holder;
            HVH.mBankNameHeader.setText(mBank.mBankName);
            HVH.mBankAddressHeader.setText(HVH.mBankAddressHeader.getContext().getText(R.string.address) + " " + mBank.mAddress);
            HVH.mBankPhoneNumberHeader.setText(HVH.mBankPhoneNumberHeader.getContext().getText(R.string.tel_number) + " " + mBank.mPhoneNumber);
            HVH.mBankEmailHeader.setText(HVH.mBankEmailHeader.getContext().getText(R.string.email) + " " + mBank.mLink);
            HVH.mCurrenciesNameSablon.setText(R.string.currencies_name);
            HVH.mBidSablon.setText(R.string.ask);
            HVH.mAskSablon.setText(R.string.bid);
        } else
        {
            setChangeCurrencies(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mBank.mCurrencies.size() + 1;
    }

    public void setChangeCurrencies(RecyclerView.ViewHolder _holder, int _position){
        CurrenciesViewHolder CVH = (CurrenciesViewHolder) _holder;
        CVH.mCurrenciesName.setText(mBank.mCurrencies.get(_position - 1).mCurrenciesFullName);
        /////////
        setChangeAsk(CVH, _position);
        setChangeBid(CVH, _position);

        CVH.mAsk.setText(mBank.mCurrencies.get(_position-1).mAsk);
        CVH.mBid.setText(mBank.mCurrencies.get(_position-1).mBid);
    }

    public void setChangeAsk(CurrenciesViewHolder _viewHolder, int _position){
        switch (mBank.mCurrencies.get(_position - 1).mChangeAsk){
            case Constants.CURRENCIES_UP:///////UP////////
                _viewHolder.mAsk.setTextColor(ContextCompat.getColor(_viewHolder.mAsk.getContext(), R.color.currencies_up_color));
                _viewHolder.mImageAsk.setImageResource(R.drawable.ic_green_arrow_up);
                break;
            case Constants.CURRENCIES_DOWN:////////DOWN/////
                _viewHolder.mAsk.setTextColor(ContextCompat.getColor(_viewHolder.mAsk.getContext(), R.color.currencies_down_color));
                _viewHolder.mImageAsk.setImageResource(R.drawable.ic_red_arrow_down);
                break;
            case Constants.CURRENCIES_NOT_CHANGE://///DEFAULT//////
                _viewHolder.mAsk.setTextColor(ContextCompat.getColor(_viewHolder.mAsk.getContext(), R.color.text_home));
                _viewHolder.mImageAsk.setImageResource(android.R.color.transparent);
                break;
        }
    }

    public void setChangeBid(CurrenciesViewHolder _viewHolder, int _position){
        switch (mBank.mCurrencies.get(_position - 1).mChangeBid){
            case Constants.CURRENCIES_UP:///////UP////////
                _viewHolder.mBid.setTextColor(ContextCompat.getColor(_viewHolder.mBid.getContext(), R.color.currencies_up_color));
                _viewHolder.mImageBid.setImageResource(R.drawable.ic_green_arrow_up);
                break;
            case Constants.CURRENCIES_DOWN:////////DOWN/////
                _viewHolder.mBid.setTextColor(ContextCompat.getColor(_viewHolder.mBid.getContext(), R.color.currencies_down_color));
                _viewHolder.mImageBid.setImageResource(R.drawable.ic_red_arrow_down);
                break;
            case Constants.CURRENCIES_NOT_CHANGE://///DEFAULT//////
                _viewHolder.mBid.setTextColor(ContextCompat.getColor(_viewHolder.mBid.getContext(), R.color.text_home));
                _viewHolder.mImageBid.setImageResource(android.R.color.transparent);
                break;
        }
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView mBankNameHeader;
        public TextView mBankAddressHeader;
        public TextView mBankPhoneNumberHeader;
        public TextView mBankEmailHeader;
        public TextView mCurrenciesNameSablon;
        public TextView mBidSablon;
        public TextView mAskSablon;

        public HeaderViewHolder(View v){
            super(v);
            mBankNameHeader =          (TextView) v.findViewById(R.id.bankNameHeader);
            mBankAddressHeader =       (TextView) v.findViewById(R.id.bankAdressHeader);
            mBankPhoneNumberHeader =   (TextView) v.findViewById(R.id.bankPhoneNumberHeader);
            mBankEmailHeader =         (TextView) v.findViewById(R.id.bankEmailHeader);
            mCurrenciesNameSablon =    (TextView) v.findViewById(R.id.courrenciesNameSablon);
            mBidSablon =               (TextView) v.findViewById(R.id.bidSablon);
            mAskSablon =               (TextView) v.findViewById(R.id.askSablon);

        }

    }

    public class CurrenciesViewHolder extends RecyclerView.ViewHolder {
        public TextView mCurrenciesName;
        public TextView mBid;
        public TextView mAsk;
        public ImageView mImageAsk;
        public ImageView mImageBid;


        public CurrenciesViewHolder(View v){
            super(v);
            mCurrenciesName =   (TextView) v.findViewById(R.id.courrenciesName);
            mAsk =               (TextView) v.findViewById(R.id.ask);
            mBid =               (TextView) v.findViewById(R.id.bid);
            mImageAsk =          (ImageView) v.findViewById(R.id.askImage);
            mImageBid =          (ImageView) v.findViewById(R.id.bidImage);
        }

    }

}
