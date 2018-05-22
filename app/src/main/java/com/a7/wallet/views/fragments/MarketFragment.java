package com.a7.wallet.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.a7.wallet.R;
import com.a7.wallet.models.Market;
import com.a7.wallet.views.widgets.BaseRecyclerViewAdapter;
import com.a7.wallet.views.widgets.RecycleViewDivider;
import com.a7.wallet.views.widgets.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viosonlee
 * on 2018/5/21.
 * for
 */
public class MarketFragment extends Fragment {
    private BaseRecyclerViewAdapter<Market> adapter;
    private RecyclerView list;
    private List<Market> data = new ArrayList<>();

    public static MarketFragment newInstance() {
        Bundle args = new Bundle();
        MarketFragment fragment = new MarketFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayout.HORIZONTAL, 1,
                getActivity().getResources().getColor(R.color.lineColor)));
        adapter = new BaseRecyclerViewAdapter<Market>(getContext(), data) {

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Market market) {
                holder.setText(R.id.short_name, market.shortCurrencyName);
                holder.setText(R.id.name, market.currencyName);
                holder.setText(R.id.coin_number, market.currencyNumber + "");
                holder.setText(R.id.coin_value, "￥" + market.currencyValue);
                holder.getImageView(R.id.icon).setImageResource(market.iconId);
                if (market.gains > 0) {
                    holder.setText(R.id.gains, "+" + market.gains + "%");
                    holder.getTextView(R.id.gains).setVisibility(View.VISIBLE);
                    holder.getTextView(R.id.drop).setVisibility(View.GONE);
                } else {
                    holder.setText(R.id.drop,  market.gains + "%");
                    holder.getTextView(R.id.drop).setVisibility(View.VISIBLE);
                    holder.getTextView(R.id.gains).setVisibility(View.GONE);
                }
            }

            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.item_market;
            }

        };
        list.setAdapter(adapter);
        View header = getActivity().getLayoutInflater().inflate(R.layout.header_market, null);
        adapter.addHeaderView(header);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        Market m1 = new Market();
        m1.gains = 0.82;
        m1.currencyValue = 0.00;
        m1.currencyNumber = 0.00;
        m1.shortCurrencyName = "AQC";
        m1.currencyName = "AoqiCoin";
        m1.iconId = R.mipmap.icon;

        Market m2 = new Market();
        m2.gains = 0.82;
        m2.currencyValue = 0.00;
        m2.currencyNumber = 0.00;
        m2.shortCurrencyName = "BTC";
        m2.currencyName = "BitCoin";
        m2.iconId = R.drawable.ic_btc;

        Market m3 = new Market();
        m3.gains = -4.76;
        m3.currencyValue = 0.00;
        m3.currencyNumber = 0.00;
        m3.shortCurrencyName = "ETH";
        m3.currencyName = "Ethereum";
        m3.iconId = R.drawable.ic_eth;
        data.add(m1);
        data.add(m2);
        data.add(m3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_market, null);
    }

}
