package com.example.hm.convertlab_mvp_dagger_rxjava.data.modell;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ferenc on 2016.04.21..
 */
public class Currencies implements Serializable {

    public String CurrenciesName;
    @SerializedName("ask") public String mCurrenciesFullName;
    @SerializedName("ask") public String mAsk;
    @SerializedName("bid") public String mBid;
    public int mChangeAsk;
    public int mChangeBid;

    public String getCanvasAsk(){
        double canvasAsk = Double.parseDouble(mAsk);
        String result = String.format("%.2f", canvasAsk);

        return result ;
    }

    public String getCanvasBid(){
        double canvasBid = Double.parseDouble(mBid);
        String result = String.format("%.2f", canvasBid);
        return result;
    }
}
