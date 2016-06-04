package com.qianfeng.ning.project_wallpaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.adapter.RecomAdapter;
import com.qianfeng.ning.project_wallpaper.utils.Url_utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ning on 2016/5/30.
 */
public class RecommandFragment extends Fragment {


    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    //viewpager的数据源（用于填充viewpager的fragment）
    ArrayList<Fragment> list = new ArrayList<Fragment>();
    private RecomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recommand_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
//        adapter.notifyDataSetChanged();
        initAdapter();
        tablayout.setupWithViewPager(viewPager);
    }

    private void initAdapter() {
        adapter = new RecomAdapter(getFragmentManager(),list);
        viewPager.setAdapter(adapter);
    }

    private void initData() {

        BaseFragment bf_zuixin = new BaseFragment();
        Bundle args = new Bundle();
        args.putString("url", Url_utils.ZUIXIN_URL);
        bf_zuixin.setArguments(args);
        list.add(bf_zuixin);

        BaseFragment bf_remen = new BaseFragment();
        Bundle args1 = new Bundle();
        args.putString("url", Url_utils.REMEN_URL);
        bf_remen.setArguments(args);
        list.add(bf_remen);

        BaseFragment bf_suiji = new BaseFragment();
        Bundle args2 = new Bundle();
        args.putString("url", Url_utils.SUIJI_URL);
        bf_suiji.setArguments(args);
        list.add(bf_suiji);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

