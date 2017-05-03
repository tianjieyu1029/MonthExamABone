package com.bwie.test.monthexamabone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bwie.test.monthexamabone.fragments.Fragment1;
import com.bwie.test.monthexamabone.fragments.Fragment2;
import com.bwie.test.monthexamabone.fragments.Fragment3;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends FragmentActivity {

    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private FragmentManager manager;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragment1 = new Fragment1();
        transaction.add(R.id.linearMain, fragment1, "tag1");
        fragment2 = new Fragment2();
        transaction.add(R.id.linearMain, fragment2, "tag2");
        fragment3 = new Fragment3();
        transaction.add(R.id.linearMain, fragment3,"tag3");
        transaction.show(fragment1);
        transaction.hide(fragment2);
        transaction.hide(fragment3);
        transaction.commit();

    }

    private void initView() {
        button1 = (RadioButton) findViewById(R.id.button1);
        button2 = (RadioButton) findViewById(R.id.button2);
        button3 = (RadioButton) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.show(fragment1);
                transaction.hide(fragment2);
                transaction.hide(fragment3);
                transaction.commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.show(fragment2);
                transaction.hide(fragment1);
                transaction.hide(fragment3);
                transaction.commit();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.show(fragment3);
                transaction.hide(fragment1);
                transaction.hide(fragment2);
                transaction.commit();
            }
        });
    }

    public void login() {
        UMShareAPI.get(this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(MainActivity.this, "Authorize succeed", Toast.LENGTH_SHORT).show();
            String name = data.get("name");
            String iconurl = data.get("iconurl");
            info.getInfo(name,iconurl);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    private QQInfo info;
    public interface QQInfo{
        void getInfo(String name,String icon);
    }
    public void setQQInfo(QQInfo info){
        this.info = info;
    }
}
