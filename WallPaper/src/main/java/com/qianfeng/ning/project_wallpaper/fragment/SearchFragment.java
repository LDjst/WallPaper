package com.qianfeng.ning.project_wallpaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.adapter.Search_Adapter;
import com.qianfeng.ning.project_wallpaper.bean.HotBean;
import com.qianfeng.ning.project_wallpaper.bean.ListBean;
import com.qianfeng.ning.project_wallpaper.bean.MoreBean;
import com.qianfeng.ning.project_wallpaper.request.WallPaperRequest;
import com.qianfeng.ning.project_wallpaper.utils.MyApp;
import com.qianfeng.ning.project_wallpaper.utils.Url_utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ning on 2016/5/30.
 */
public class SearchFragment extends Fragment {
    @Bind(R.id.recycleView)
    RecyclerView recycleView;
    private Search_Adapter adapter;

    private ArrayList<HotBean.DataBean>hot_list = new ArrayList<>();
    private ArrayList<ListBean.DataBean>list_list = new ArrayList<>();
    private ArrayList<MoreBean.DataBean.TopicBean>more_list = new ArrayList<>();
    private RequestQueue queue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化队列
        queue = Volley.newRequestQueue(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        //初始化控件
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化数据
        initData();
        //设置适配器
        initAdapter();
    }

    private void initData() {
        //读取热门数据
        getHotData();
        //读取更多
        getMoreData();
        //读取list
        getListData();
    }

    private void getListData() {
        //封装一个requestqueue请求对象
        WallPaperRequest<ListBean> searhRequest = new WallPaperRequest<ListBean>(ListBean.class, Url_utils.SEARCH_LIST_URL,
                new Response.Listener<ListBean>() {
                    @Override
                    public void onResponse(ListBean response) {
                        //获取数据
                        if (response != null && response.getData() != null) {
                            list_list.addAll(response.getData());
                            adapter.updateList(list_list);
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(searhRequest);

    }

    private void getMoreData() {
        //封装一个requestqueue请求对象
        WallPaperRequest<MoreBean> searhRequest = new WallPaperRequest<MoreBean>(MoreBean.class, Url_utils.SEARCH_MORE_URL,
                new Response.Listener<MoreBean>() {
                    @Override
                    public void onResponse(MoreBean response) {
                        //获取数据
                        if (response != null && response.getData() != null) {
                            more_list.addAll(response.getData().getTopic());
                            adapter.updateMore(more_list);
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(searhRequest);

    }

    private void getHotData(){
        //封装一个requestqueue请求对象
        WallPaperRequest<HotBean> searhRequest = new WallPaperRequest<HotBean>(HotBean.class, Url_utils.SEARCH_HOT_URL,
                new Response.Listener<HotBean>() {
                    @Override
                    public void onResponse(HotBean response) {
                        //获取数据
                        if (response != null && response.getData() != null) {
                            hot_list.addAll(response.getData());
                            adapter.updateHot(hot_list);
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });
        queue.add(searhRequest);
    }


    private void initAdapter() {
        ImageLoader loader = ((MyApp)getActivity().getApplication()).getLoader();
        adapter = new Search_Adapter(getActivity(),loader);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
