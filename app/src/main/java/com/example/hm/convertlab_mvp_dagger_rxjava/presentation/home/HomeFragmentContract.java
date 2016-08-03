package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.home;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.BankResponse;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.base.BaseFragmentPrsesenter;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.base.BaseTouchView;

/**
 * Created by Ferenc on 2016.04.18..
 */
public interface HomeFragmentContract {

     interface HomeFragmentView extends BaseTouchView{
         void setBankList(BankResponse _ankResponse);
         void touchDetails(Banks _bank);
         void showProgressBar(Boolean _visibility);
    }

     interface HomeFragmentPresenter {
         void loadBanks();
         void setView(HomeFragmentContract.HomeFragmentView _View);

         void initUriForPhone(int _itemPosition);
         void initUriForMap(int _itemPosition);
         void initUriForLink(int _itemPosition);
         void startDetailFragment(Banks _banks);
    }

}
