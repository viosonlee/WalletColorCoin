package com.a7.wallet.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a7.wallet.R;
import com.a7.wallet.network.Requester;
import com.a7.wallet.utils.AppDataController;
import com.a7.wallet.views.acitivities.ExchangeHistoryActivity;
import com.a7.wallet.views.acitivities.LoginActivity;

import lee.vioson.network.core.BaseApiException;
import lee.vioson.network.core.BaseObserver;
import lee.vioson.network.core.BaseResponse;

/**
 * Created by viosonlee
 * on 2018/5/21.
 * for
 */
public class MemberFragment extends Fragment {
    public static MemberFragment newInstance() {
        Bundle args = new Bundle();
        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member, null);
    }

    private TextView logoutBtn;
    private TextView name;
    private TextView memberAmount;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent(view);
    }

    private void initView(View view) {
        name = view.findViewById(R.id.name);
        name.setText(AppDataController.getUserInfo().getNick());
        logoutBtn = view.findViewById(R.id.logout);
        logoutBtn.setVisibility(AppDataController.isLogin() ? View.VISIBLE : View.GONE);
        memberAmount = view.findViewById(R.id.assets_number);
        memberAmount.setText(AppDataController.getUserInfo().getDiamondCoin() + "");
    }

    private void initEvent(View view) {
        logoutBtn.setOnClickListener(this::logout);
        view.findViewById(R.id.exchange_list).setOnClickListener(this::exchangeList);
    }

    private void exchangeList(View view) {
        startActivity(new Intent(getContext(), ExchangeHistoryActivity.class));
    }

    private void logout(View view) {
        Requester.logout(new BaseObserver<BaseResponse>() {
            @Override
            protected void onHandleSuccess(BaseResponse data) {
                AppDataController.logout();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }

            @Override
            protected void onHandleError(BaseApiException e) {
                super.onHandleError(e);
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
