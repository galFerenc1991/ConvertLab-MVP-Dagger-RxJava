package com.example.hm.convertlab_mvp_dagger_rxjava.data.modell;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hm on 13.04.2016.
 */
public class BankResponse {
    @SerializedName("organizations") public List<Banks> mBanks = new ArrayList<>();
}
