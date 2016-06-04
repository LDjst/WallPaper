package com.qianfeng.ning.project_wallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qianfeng.ning.project_wallpaper.R;
import com.qianfeng.ning.project_wallpaper.bean.Category_Bean;
import com.qianfeng.ning.project_wallpaper.utils.Url_utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ning on 2016/5/31.
 */
public class CategoryAdapter extends BaseAdapter {
    private List<Category_Bean.DataBean> data;
    private Context context = null;
    private LayoutInflater inflater = null;
    private ImageLoader loader;
    private static final String TAG = "CategoryAdapter";

    public CategoryAdapter(Context context, List<Category_Bean.DataBean> data, ImageLoader loader) {
        this.data = data;
        this.context = context;
        this.loader = loader;
        //获取系统填充器
        inflater = LayoutInflater.from(context);
    }

    /**
     * 更新数据并刷新适配器
     *
     * @param data
     */
    public void updateList(List<Category_Bean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_category, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category_Bean.DataBean list = data.get(position);
        holder.picCategoryName.setText(list.getPicCategoryName());
        holder.descWords.setText(list.getDescWords());
//        holder.imageView.setImageURI(list.setCategoryPic());
        //通过imageLoader对象调用displayImage方法设置iv显示图片
//        loader.displayImage(Url_utils.CATEGORY_BASEURL, holder.imageView);
        loader.displayImage(list.getCategoryPic(),holder.imageView);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.picCategoryName)
        TextView picCategoryName;
        @Bind(R.id.descWords)
        TextView descWords;
        @Bind(R.id.imageView)
        ImageView imageView;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}