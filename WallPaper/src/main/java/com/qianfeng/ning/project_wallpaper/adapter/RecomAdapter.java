package com.qianfeng.ning.project_wallpaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ning on 2016/6/1.
 */
public  class RecomAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list = null;
    public RecomAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "最新";
        }else if(position == 1){
            return "热门";
        }else if (position == 2){
            return "随机";
        }
        return null;
    }
}