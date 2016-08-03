package com.example.hm.convertlab_mvp_dagger_rxjava.data.repository.cache;

import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.dao.BankDao;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by Ferenc on 2016.05.12..
 */
public class CacheImpl implements Cache {
    private Executor mExecutor;
    private BankDao mBankDao;

    @Inject
    public CacheImpl(Executor _executor,  BankDao _bankDao){
        mBankDao = _bankDao;
        mExecutor = _executor;


    }

    @Override
    public void cacheFill(final BankResponse _bankResponce) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mBankDao.deleteDatabase();
                mBankDao.addAllBanks(_bankResponce.mBanks);
                Log.d("Cache","Fill");
            }
        });

    }
}
