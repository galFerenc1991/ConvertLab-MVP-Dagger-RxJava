package com.example.hm.convertlab_mvp_dagger_rxjava.data.modell;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hm on 13.04.2016.
 */
public class Banks implements Serializable{
    @SerializedName("id") public String mBankID;
    @SerializedName("title") public String mBankName;
    @SerializedName("regionId") public String mRegion;
    @SerializedName("cityId") public String mCity;
    @SerializedName("phone") public String mPhoneNumber;
    @SerializedName("address") public String mAddress;
    @SerializedName("link") public String mLink;
    @SerializedName("currencies") public List<Currencies> mCurrencies;
}
