package com.a7.wallet.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a7.wallet.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by viosonlee
 * on 2018/5/12.
 * for
 */
public class HomeFragment extends Fragment {
    private View view;
    private ConvenientBanner<Integer> convenientBanner;
    private Integer[] localImages = {
            R.drawable.pic_banner_1,
            R.drawable.pic_banner_2,
            R.drawable.pic_banner_3,
            R.drawable.pic_banner_4,
            R.drawable.pic_banner_5
    };

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        convenientBanner = view.findViewById(R.id.banner);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Integer> images = Arrays.asList(localImages);
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.banner_item;
            }
        }, images).setPageIndicator(new int[]{R.drawable.ic_no, R.drawable.ic_in})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setCanLoop(true).startTurning();

    }

    public class LocalImageHolderView extends Holder<Integer> {
        private ImageView imageView;

        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.image_view);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void updateUI(Integer data) {
            imageView.setImageResource(data);
        }
    }
}
