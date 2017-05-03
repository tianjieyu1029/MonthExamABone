package com.bwie.test.monthexamabone.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bwie.test.monthexamabone.R;
import com.bwie.test.monthexamabone.adapters.MyAdapter;
import com.bwie.test.monthexamabone.beans.JsonBean;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjieyu on 2017/5/3.
 */

public class Fragment1 extends Fragment {
    private Banner banner;
    private ListView f1_listView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment1_layout, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        f1_listView = (ListView) view.findViewById(R.id.f1_listView);
    }

    private void initData() {
        String url = "http://result.eolinker.com/8DZ29G33c1f62318706dd73419ae7097c5e5d7e95b197ae?uri=book_list";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                List<JsonBean.ResultBean.BookListBean> bookList = jsonBean.getResult().getBookList();
                ArrayList<String> list = new ArrayList<String>();
                for (int i= 0;i<bookList.size();i++){
                    list.add(bookList.get(i).getCoverImg());
                }
                banner.setImages(list).setImageLoader(new GlideImageLoader()).start();
                MyAdapter adapter = new MyAdapter(getActivity(),bookList);
                f1_listView.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
           // Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
           /* Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);*/
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
      /*  @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
            return simpleDraweeView;
        }*/
    }
}
