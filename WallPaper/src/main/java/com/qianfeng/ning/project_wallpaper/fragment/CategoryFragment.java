package com.qianfeng.ning.project_wallpaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;

import java.util.ArrayList;
import java.util.List;

import com.qianfeng.ning.project_wallpaper.adapter.CategoryAdapter;
import com.qianfeng.ning.project_wallpaper.bean.Category_Bean;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.qianfeng.ning.project_wallpaper.request.WallPaperRequest;
import com.qianfeng.ning.project_wallpaper.utils.MyApp;
import com.qianfeng.ning.project_wallpaper.utils.Url_utils;

/**
 * Created by Ning on 2016/5/30.
 */
public class CategoryFragment extends Fragment {
    private static final String TAG = "CategoryFragment";
    @Bind(R.id.category_listView)
    ListView categoryListView;
    private RequestQueue queue;
    private CategoryAdapter adapter;
    private List<Category_Bean.DataBean>fragmentlist = new ArrayList<>();
    private ImageLoader loader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化队列
        queue = Volley.newRequestQueue(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 通过ButterKnife初始化控件

        //通过Volley进行网络读取数据并进行解析
        initData();
        loader = ((MyApp)getActivity().getApplication()).getLoader();
        //封装并设置适配器
        setAdapter();

    }

    private void setAdapter() {
        adapter = new CategoryAdapter(getContext(),fragmentlist,loader);
        categoryListView.setAdapter(adapter);

    }

    private void initData() {
        //封装一个RequestQueue对象
        WallPaperRequest<Category_Bean> request = new WallPaperRequest<Category_Bean>(
                Category_Bean.class,
                Url_utils.CATEGORY_BASEURL,
                new Response.Listener<Category_Bean>() {
                    @Override
                    public void onResponse(Category_Bean response) {
                        //获取data集合
                       List<Category_Bean.DataBean> data = response.getData();
                        //将data集合存入fragment的数据集合中，并刷新适配器
                        Log.i(TAG, "onResponse: "+data.toString());
//                        fragmentlist.addAll(data);
//                        adapter.notifyDataSetChanged();
                        adapter.updateList(data);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: "+error.toString());
                    }
                }
        );
        queue.add(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
