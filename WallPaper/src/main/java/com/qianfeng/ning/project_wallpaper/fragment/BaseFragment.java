package com.qianfeng.ning.project_wallpaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.adapter.RecyclerAdapter;
import com.qianfeng.ning.project_wallpaper.bean.Recomm_Bean;
import com.qianfeng.ning.project_wallpaper.request.WallPaperRequest;
import com.qianfeng.ning.project_wallpaper.utils.MyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ning on 2016/6/1.
 */
public class BaseFragment extends Fragment {
    private List<Recomm_Bean.DataBean.WallpaperListInfoBean> list = new ArrayList<>();
    private List<Integer> list_height = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private ImageLoader loader;
    private static final String TAG = "BaseFragment";
    private RecyclerAdapter adapter;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化队列
        queue = Volley.newRequestQueue(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        url = getArguments().getString("url");
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        /*设置布局管理器，作用是设置recycleView要显示的效果，有3种效果
         分别是瀑布流效果，listView和gridView。*/

//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        loader = ((MyApp) getActivity().getApplication()).getLoader();
        //设置要显示的数据源
        initData();
        //设置适配器
        initAdapter();

    }

    private void initHeightData() {
        for (int i = 0; i < list.size(); i++) {
            list_height.add(new Random().nextInt(400) + 100);
        }
    }

    private void initAdapter() {
        adapter = new RecyclerAdapter(getActivity(), list, loader, list_height);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        //封装一个RequestQueue对象
        WallPaperRequest<Recomm_Bean> request = new WallPaperRequest<Recomm_Bean>(
                Recomm_Bean.class,
                url,
                new Response.Listener<Recomm_Bean>() {
                    @Override
                    public void onResponse(Recomm_Bean response) {
                        //获取data集合
                        List<Recomm_Bean.DataBean.WallpaperListInfoBean> data = response.getData().getWallpaperListInfo();
                        Log.i(TAG, "onResponse: basefragment" + data.toString());
                        list.addAll(data);
//                      adapter.updateList(data);
//                        initHeightData();
                        adapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: " + error.toString());
                    }
                }
        );
        queue.add(request);
    }

}
