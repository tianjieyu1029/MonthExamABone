package com.bwie.test.monthexamabone.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.test.monthexamabone.R;
import com.bwie.test.monthexamabone.beans.JsonBean;

import java.util.List;

/**
 * Created by tianjieyu on 2017/5/3.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<JsonBean.ResultBean.BookListBean> list;

    public MyAdapter(Context context, List<JsonBean.ResultBean.BookListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_layout,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            holder.textView1 = (TextView) convertView.findViewById(R.id.item_text1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.item_text2);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getCoverImg()).into(holder.imageView);
        holder.textView1.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getType());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
