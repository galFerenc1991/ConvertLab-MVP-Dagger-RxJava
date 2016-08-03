package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.R;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.BankDatabase;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home.HomeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar topToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BankDatabase.init(this);
        Log.d("Life", "ActivitiOnCreated");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragCont, new HomeFragment())
                    .commit();
        }
    }
    public Toolbar getTopToolBar() {
        return topToolBar;
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.sIsActive = true;
    }

    @Override
    protected void onStop() {
        MyApplication.sIsActive = false;
        super.onStop();
    }
}
