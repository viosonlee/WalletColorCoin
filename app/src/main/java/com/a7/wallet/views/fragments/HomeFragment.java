package com.a7.wallet.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a7.wallet.R;
import com.a7.wallet.models.Coin;
import com.a7.wallet.views.widgets.BaseRecyclerViewAdapter;
import com.a7.wallet.views.widgets.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new, null);
    }

    private RecyclerView list;
    private List<Coin> data = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter<Coin>(getActivity(), data) {
            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Coin coin) {
                holder.setText(R.id.short_name, coin.shortName);
                holder.setText(R.id.name, coin.name);
                holder.setText(R.id.number, coin.number + "");
                holder.setText(R.id.value, "￥" + coin.value);
                holder.setText(R.id.present, coin.present + "%");
                holder.getImageView(R.id.icon).setImageResource(coin.iconId);
            }

            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.item_home_list;
            }
        };
        list.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        Coin c0 = new Coin();
        c0.value = 0.00;
        c0.iconId = R.mipmap.icon;
        c0.name = "AoqiCoin";
        c0.shortName = "AQC";
        c0.number = 0.00;
        c0.present = 45;
        Coin c1 = new Coin();
        c1.value = 0.00;
        c1.iconId = R.drawable.ic_btc;
        c1.name = "BitCoin";
        c1.shortName = "BTC";
        c1.number = 0.00;
        c1.present = 23;
        Coin c2 = new Coin();
        c2.value = 0.00;
        c2.iconId = R.drawable.ic_eth;
        c2.name = "Ethereum";
        c2.shortName = "ETH";
        c2.number = 0.00;
        c2.present = 32;

        data.add(c0);
        data.add(c1);
        data.add(c2);
    }
}
