package com.qianfeng.ning.project_wallpaper.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;

import java.util.ArrayList;

/**
 * Created by Ning on 2016/6/3.
 */
public class Search_PagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private ArrayList<View> pagerList ;
    private ImageLoader loader;

    public void setPagerList(ArrayList<View> pagerList,ImageLoader loader) {
        this.pagerList = pagerList;
        this.loader = loader;
    }

    public Search_PagerAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return pagerList == null?0:1000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pagerList.get(position%pagerList.size());
        container.addView(view);
        ImageView iv1 = (ImageView) view.findViewById(R.id.pager_iv1);
        ImageView iv2 = (ImageView) view.findViewById(R.id.pager_iv2);

        loader.displayImage(iv1.getTag().toString(),iv1);
        loader.displayImage(iv2.getTag().toString(),iv2);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagerList.get(position%pagerList.size()));
    }
}
