package com.a7.wallet.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a7.wallet.R;
import com.a7.wallet.models.News;
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
public class NewsFragment extends Fragment {
    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, null);
    }

    private RecyclerView list;
    private List<News> data = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayout.HORIZONTAL, 1,
                getActivity().getResources().getColor(R.color.lineColor)));
        list.setAdapter(new BaseRecyclerViewAdapter<News>(getActivity(), data) {

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, News news) {
                holder.setText(R.id.news_title, news.title);
                holder.setText(R.id.news_time, news.time);
                holder.setText(R.id.news_source, news.source);
            }

            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.item_news;
            }

        });
        ImageView imageView = new ImageView(getActivity());
        ViewGroup.LayoutParams layoutParams = new RecyclerView.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setAdjustViewBounds(true);
        imageView.setImageResource(R.drawable.pic_news_banner);
        ((BaseRecyclerViewAdapter) list.getAdapter()).addHeaderView(imageView);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        //模拟数据
        for (int i = 0; i < 3; i++) {
            News news = new News();
            news.source = "token.im";
            news.time = "12小时前";
            news.title = "闪兑交易大赛颁发压轴大奖";
            data.add(news);
        }
    }
}
