package com.bwie.test.monthexamabone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.test.monthexamabone.MainActivity;
import com.bwie.test.monthexamabone.R;

/**
 * Created by tianjieyu on 2017/5/3.
 */

public class Fragment3 extends Fragment implements View.OnClickListener {
    private ImageView f3_image;
    private TextView f3_text;
    private Button f3_button_qq;
    private Button f3_button_share;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment3_layout, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setQQInfo(new MainActivity.QQInfo() {
            @Override
            public void getInfo(String name, String icon) {
                Glide.with(getActivity()).load(icon).into(f3_image);
                f3_text.setText(name);
            }
        });
    }

    private void initView(View view) {
        f3_image = (ImageView) view.findViewById(R.id.f3_image);
        f3_text = (TextView) view.findViewById(R.id.f3_text);
        f3_button_qq = (Button) view.findViewById(R.id.f3_button_qq);
        f3_button_share = (Button) view.findViewById(R.id.f3_button_share);

        f3_button_qq.setOnClickListener(this);
        f3_button_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f3_button_qq:
                MainActivity activity = (MainActivity) getActivity();
                activity.login();
                break;
            case R.id.f3_button_share:

                break;
        }
    }
}
