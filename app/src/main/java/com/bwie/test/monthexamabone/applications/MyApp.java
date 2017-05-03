package com.bwie.test.monthexamabone.applications;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

/**
 * Created by tianjieyu on 2017/5/3.
 */

public class MyApp extends Application {
    {
        //1106034403  XbCJuzd68e1ipMWu
        PlatformConfig.setQQZone("1106034403", "XbCJuzd68e1ipMWu");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        UMShareAPI.get(this);
    }
}
