package com.a7.wallet.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by viosonlee
 * on 2018/5/21.
 * for
 */
public class HomeFragment extends Fragment {
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
