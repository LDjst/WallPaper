package com.qianfeng.ning.project_wallpaper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.bean.Recomm_Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ning on 2016/6/1.
 */

/*泛型填的是viewholder类型
* 1.创建一个ViewHolder子类
* 2.按要求重写构造方法
* 3.声明item布局文件中的变量
* 4.在ViewHolder子类的构造方法中初始化变量
* 5.按照要重写Adapter中的子类中的构造方法
* */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder> {
    private Context context;
    private List<Recomm_Bean.DataBean.WallpaperListInfoBean> data = null;
    private ImageLoader loader;
    private List<Integer>list_height = null;

    public RecyclerAdapter(Context context, List<Recomm_Bean.DataBean.WallpaperListInfoBean> data,ImageLoader loader,List<Integer>list_height){
        this.context = context;
        this.data = data;
        this.loader = loader;
        this.list_height = list_height;

    }

    //需要创建Myholder对象是调用
    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Myholder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false));
    }
    //用于向holder对象中填充数据，当item显示时，调用此方法
    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        //获取原有的layoutParam对象
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        params.height = list_height.get(position);

        holder.itemView.setLayoutParams(params);
        loader.displayImage(data.get(position).getWallPaperMiddle(),holder.iv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Myholder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public Myholder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_imageView);

        }
    }
}
