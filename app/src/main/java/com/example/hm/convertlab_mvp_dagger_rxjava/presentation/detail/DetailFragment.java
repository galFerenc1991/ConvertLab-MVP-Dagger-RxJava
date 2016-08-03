package com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hm.convertlab_mvp_dagger_rxjava.R;
import com.example.hm.convertlab_mvp_dagger_rxjava.data.modell.Banks;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.application.MainActivity;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.adapter.DetailAdapter;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.di.DaggerDetailComponent;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.detail.di.DetailModule;
import com.example.hm.convertlab_mvp_dagger_rxjava.presentation.dialog.MyDialogFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ferenc on 2016.04.21..
 */
public class DetailFragment extends Fragment implements DetailFragmentContract.DetailFragmentView, View.OnClickListener {
    @Bind(R.id.menu)
    FloatingActionMenu fabMenu;
    @Bind(R.id.menuLink)
    FloatingActionButton mFABMap;
    @Bind(R.id.menuMap)
    FloatingActionButton mFABLink;
    @Bind(R.id.menuCall)
    FloatingActionButton mFABCall;
    @Bind(R.id.detail_recycler_view)
    RecyclerView mDetailRecyclerView;
    @Inject
    DetailFragmentContract.DetailFragmentPresenter mDetailFragmentPresenter;
    @Inject
    DetailAdapter mDetailsAdapter;

    private View view;
    private Banks mBank;
    private RecyclerView.LayoutManager mDetailLayoutManager;

    private MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mBank = (Banks) getArguments().getSerializable("bank");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.menuCall:
                mDetailFragmentPresenter.initUriForPhone(mBank);
                break;
            case R.id.menuLink:
                mDetailFragmentPresenter.initUriForLink(mBank);
                break;
            case R.id.menuMap:
                mDetailFragmentPresenter.initUriForMap(mBank);
                break;

        }
    }

    public static DetailFragment newInstance(Banks _bank) {

        Bundle args = new Bundle();
        args.putSerializable("bank", _bank);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setTitle(mBank.mBankName);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        inject();
        mDetailRecyclerView.setAdapter(mDetailsAdapter);
        mDetailFragmentPresenter.setView(this);
        mActivity.getTopToolBar().setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.canvas_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.canvas:
                MyDialogFragment myDialogFragment = MyDialogFragment.newInstance(mBank);
                myDialogFragment.show(getActivity().getSupportFragmentManager(), "Dialog Fragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_fragment, container, false);
        ButterKnife.bind(this, view);
        mDetailRecyclerView.setHasFixedSize(true);

        mDetailLayoutManager = new LinearLayoutManager(getActivity());
        mDetailRecyclerView.setLayoutManager(mDetailLayoutManager);

        findFabUI();

        return view;
    }

    @Override
    public void findFabUI() {
        fabMenu.setClosedOnTouchOutside(true);
        mFABMap.setOnClickListener(this);
        mFABCall.setOnClickListener(this);
        mFABLink.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    private void inject() {
        DaggerDetailComponent.builder()
                .detailModule(new DetailModule(mBank))
                .build().inject(this);
    }


    @Override
    public void touchLink(String _uriForLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_uriForLink));
        getContext().startActivity(intent);
    }

    @Override
    public void touchMap(String _uriForMAp) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_uriForMAp));
        getContext().startActivity(intent);
    }

    @Override
    public void touchPhone(String _uriForPhone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(_uriForPhone));
        getContext().startActivity(intent);

    }

}