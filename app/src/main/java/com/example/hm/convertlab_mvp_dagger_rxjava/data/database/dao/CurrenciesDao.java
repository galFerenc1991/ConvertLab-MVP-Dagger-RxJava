package com.example.hm.convertlab_mvp_dagger_rxjava.data.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.database.BankDatabase;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Currencies;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hm on 15.02.2016.
 */
public class CurrenciesDao  {

    public List<Currencies> getCurrencies(int num){
        SQLiteDatabase db = null;
        List<Currencies> exampleCurrencies = new ArrayList<>();
        Cursor c = null;
        try {
            db = BankDatabase.getInstance().getReadableDatabase();
            c = db.query(CurrenciesContract.TABLE_BANK_CURRENCIES, null, CurrenciesContract.KEY_CUR + " = ?", new String[]{String.valueOf(num)}, null, null, null);
            if (c.moveToFirst()) {
                int nameCurrenciesColIndex = c.getColumnIndex(CurrenciesContract.KEY_CURRENCIES_NAME);
                int fullNameCurrenciesColIndex = c.getColumnIndex(CurrenciesContract.KEY_CURRENCIES_FULL_NAME);
                int bidColIndex = c.getColumnIndex(CurrenciesContract.KEY_BID);
                int askColIndex = c.getColumnIndex(CurrenciesContract.KEY_ASK);

                do {
                    Currencies curExample = new Currencies();
                    curExample.CurrenciesName = c.getString(nameCurrenciesColIndex);
                    curExample.mCurrenciesFullName = c.getString(fullNameCurrenciesColIndex);
                    curExample.mAsk = c.getString(askColIndex);
                    curExample.mBid = c.getString(bidColIndex);

                    exampleCurrencies.add(curExample);
                } while (c.moveToNext());
            }else c.close();
        } catch (Exception e) {
            Log.d("Feri", "Exception", e);
        }

        return exampleCurrencies;
    }

    public void addCurrencies(long _bankKey, Banks _bank) {
        SQLiteDatabase database = BankDatabase.getInstance().getWritableDatabase();
        for (int j = 0; j < _bank.mCurrencies.size(); j++){
            ContentValues contentValues = new ContentValues();
            contentValues.put(CurrenciesContract.KEY_CURRENCIES_NAME, _bank.mCurrencies.get(j).CurrenciesName);
            contentValues.put(CurrenciesContract.KEY_CURRENCIES_FULL_NAME, _bank.mCurrencies.get(j).mCurrenciesFullName);
            contentValues.put(CurrenciesContract.KEY_ASK, _bank.mCurrencies.get(j).mAsk);
            contentValues.put(CurrenciesContract.KEY_BID, _bank.mCurrencies.get(j).mBid);
            contentValues.put(CurrenciesContract.KEY_CUR, _bankKey);


            database.insert(CurrenciesContract.TABLE_BANK_CURRENCIES, null, contentValues);
        }
    }

    public void deleteCurrencies() {
        SQLiteDatabase db = BankDatabase.getInstance().getWritableDatabase();
        db.delete(CurrenciesContract.TABLE_BANK_CURRENCIES, null, null);
    }
}
