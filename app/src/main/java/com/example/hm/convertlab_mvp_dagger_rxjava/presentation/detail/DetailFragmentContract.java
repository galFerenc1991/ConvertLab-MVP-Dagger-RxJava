package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail;

import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.base.BaseFragmentPrsesenter;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.base.BaseTouchView;

/**
 * Created by Ferenc on 2016.04.21..
 */
public interface DetailFragmentContract {
    interface DetailFragmentView extends BaseTouchView{
        void findFabUI ();
    }

    interface DetailFragmentPresenter extends BaseFragmentPrsesenter<Banks> {
        void setView(DetailFragmentContract.DetailFragmentView _View);

    }
}
