package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import javax.inject.Inject;

/**
 * Created by Ferenc on 2016.05.16..
 */
public class MyNotification {
    private static NotificationManager notificationManager;
    private static NotificationCompat.Builder mBuilder;
    private static String percentage;
    private static Context context;

//    @Inject
//    public MyNotification(final Context _context){
//        context = _context;
//    }

    public static void init(Context _context) {
        context = _context;
    }
    public static void setPercentage(int _bankSize, int _i){
        int d = _i * 100 / _bankSize ;
        percentage = String.valueOf(d);
    }


    public static void startNotification(int _bankSize, int id){
        setPercentage(_bankSize, id);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Loading Date!!!!!!!!!!")
                .setContentText("  " + percentage + " %")
                .setSmallIcon(android.R.drawable.btn_star)
                .setProgress(_bankSize, id, false);
        Notification notification = mBuilder.build();
        notificationManager.notify(100, notification);

    }

    public static void completeNotification(){
        mBuilder.setContentText("Loading complete !!!!!!!!!!!!!!")
                .setProgress(0, 0, false);
        notificationManager.notify(100, mBuilder.build());
        notificationManager.cancelAll();
    }

}