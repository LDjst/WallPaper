package com.qianfeng.ning.project_wallpaper.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Ning on 2016/5/31.
 */
public class FragmentFactory {
    public static Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case 1:
                fragment = new RecommandFragment();
                break;
            case 2:
                fragment = new CategoryFragment();
                break;
            case 3:
                fragment = new SearchFragment();
                break;
            case 4:
                fragment = new MoreFragment();
                break;
        }
        return fragment;
    }
}
