package cn.kkk.usury.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.util.UUID;

import cn.kkk.usury.R;
import cn.kkk.usury.model.net.IUserModel;
import cn.kkk.usury.model.net.OnCompleteListener;
import cn.kkk.usury.utils.MFGT;
import cn.kkk.usury.utils.OkHttpUtils;

/**
 * Created by apple on 2017/4/29.
 */

public class SplashActivity extends Activity {

    IUserModel model;
    private static final int sleepTime = 2000;
    String mac_uuid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
    }

    private void initData() {
        mac_uuid = getDeviceId(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginTemp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MFGT.gotoMainActivity(SplashActivity.this);
                SplashActivity.this.finish();
            }
        },sleepTime);

    }

    private static String getDeviceId(Context context) {
        return android.provider.Settings.Secure.getString(
                context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
    }

    private void loginTemp() {
        if (mac_uuid!=null) {

            model.loginTemp(SplashActivity.this, mac_uuid, new OnCompleteListener<String>() {
                @Override
                public void onSuccess(String result) {

                }

                @Override
                public void onError(String error) {

                }
            });
        }
    }
}
