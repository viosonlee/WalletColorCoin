package com.a7.wallet.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a7.wallet.R;

/**
 * Created by viosonlee
 * on 2018/5/12.
 * for
 */
public class ColorCoinTradeFragment extends Fragment {
    public static ColorCoinTradeFragment newInstance() {
        Bundle args = new Bundle();
        ColorCoinTradeFragment fragment = new ColorCoinTradeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coin_trade,null);
    }
}
