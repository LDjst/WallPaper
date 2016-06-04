package com.qianfeng.ning.project_wallpaper.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.bean.HotBean;
import com.qianfeng.ning.project_wallpaper.bean.ListBean;
import com.qianfeng.ning.project_wallpaper.bean.MoreBean;
import com.qianfeng.ning.project_wallpaper.view.Item_more_adv;
import com.qianfeng.ning.project_wallpaper.view.RoundAngleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ning on 2016/6/3.
 */
public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.MyViewHoler> {
    private LayoutInflater inflater;
    private ImageLoader loader;
    private ArrayList<HotBean.DataBean> hotList;
    private ArrayList<ListBean.DataBean> beanList;
    private ArrayList<MoreBean.DataBean.TopicBean> moreList;
    public Search_Adapter(Context context,ImageLoader loader){
        inflater = LayoutInflater.from(context);
        this.loader = loader;

    }

    public void updateHot (ArrayList<HotBean.DataBean> hotList) {
        this.hotList = hotList;
    }

    public void updateList(ArrayList<ListBean.DataBean> beanList) {
        this.beanList = beanList;
    }

    public void updateMore(ArrayList<MoreBean.DataBean.TopicBean> moreList){this.moreList = moreList;}
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else if (position == 1){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHoler holder = null;
        switch (viewType){
            case 0:
                holder = new HolderTypeHot(inflater.inflate(R.layout.search_main_hot,parent,false));
                break;
            case 1:
                holder = new HolderTypeMore(inflater.inflate(R.layout.search_main_more,parent,false));
                break;
            case 2:
                holder = new HolderTypeList(inflater.inflate(R.layout.search_main_list,parent,false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                HolderTypeHot holderHot = (HolderTypeHot) holder;
                if(hotList != null){
                    for (int i = 0; i <hotList.size() ; i++) {
                        final TextView tv = holderHot.hotTvs.get(i);
                        tv.setText(hotList.get(i).getKeyword());
                        loader.loadImage(hotList.get(i).getImgs().get(0),new SimpleImageLoadingListener(){
                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view, loadedImage);
//                                Bitmap bm = new RoundAngleImageView(context).getRoundBitmap(loadedImage,14);
                                tv.setBackground(new BitmapDrawable(loadedImage));
                            }
                        });
                    }
                }
                break;
            case 1:
                HolderTypeMore holderTypeMore = (HolderTypeMore) holder;
                if(moreList != null){
                    holderTypeMore.advView.updatePager(moreList,loader);
                }
                break;
            case 2:
                HolderTypeList holderTypeList = (HolderTypeList) holder;
                if(beanList != null){
                    holderTypeList.tv_title.setText(beanList.get(position-2).getKeyword());
                    loader.displayImage(beanList.get(position-2).getImgs().get(0),holderTypeList.iv_left);
                    loader.displayImage(beanList.get(position-2).getImgs().get(1),holderTypeList.iv_middle);
                    loader.displayImage(beanList.get(position-2).getImgs().get(2),holderTypeList.iv_right);
                }
            break;
        }

    }

    @Override
    public int getItemCount() {
        int count = beanList == null? 0: beanList.size()+2;
        return count;
    }

    class MyViewHoler extends RecyclerView.ViewHolder{

        public MyViewHoler(View itemView) {
            super(itemView);
        }
    }


    //写三个holder对象分别对应操作三个不同的布局
    //然后写对应的三个布局的布局文件
    class HolderTypeHot extends MyViewHoler{
        private TextView tv_car,tv_cartoon,tv_feng,tv_animal;
        private List<TextView>hotTvs = new ArrayList<>();
        public HolderTypeHot(View itemView) {
            super(itemView);
            tv_car = (TextView) itemView.findViewById(R.id.tv_car);
            tv_cartoon = (TextView) itemView.findViewById(R.id.tv_cartoon);
            tv_feng = (TextView) itemView.findViewById(R.id.tv_feng);
            tv_animal = (TextView) itemView.findViewById(R.id.tv_animal);

            hotTvs.add(tv_car);
            hotTvs.add(tv_cartoon);
            hotTvs.add(tv_feng);
            hotTvs.add(tv_animal);

        }
    }

    class HolderTypeMore extends MyViewHoler{
        //找到自定义的VIEW
        Item_more_adv advView;
        public HolderTypeMore(View itemView) {
            super(itemView);
            advView = (Item_more_adv)itemView.findViewById(R.id.more_adv);
        }
    }

    class HolderTypeList extends MyViewHoler{
        private TextView tv_title;
        private ImageView iv_left,iv_middle,iv_right;
        public HolderTypeList(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_searchlist_title);
            iv_middle = (ImageView) itemView.findViewById(R.id.iv_middle);
            iv_left = (ImageView) itemView.findViewById(R.id.iv_left);
            iv_right = (ImageView) itemView.findViewById(R.id.iv_right);
        }
    }
}
