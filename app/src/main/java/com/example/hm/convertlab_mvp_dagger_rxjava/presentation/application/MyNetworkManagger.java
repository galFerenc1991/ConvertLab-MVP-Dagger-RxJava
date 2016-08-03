package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Ferenc on 2016.05.16..
 */
public class MyNetworkManagger {

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
