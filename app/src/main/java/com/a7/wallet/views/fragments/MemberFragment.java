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
        return inflater.inflate(R.layout.fragment_member,null);
    }
}
