package cn.kkk.usury.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import cn.kkk.usury.R;
import cn.kkk.usury.utils.MFGT;

/**
 * Created by apple on 2017/4/29.
 */

public class SplashActivity extends Activity {

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MFGT.gotoMainActivity(SplashActivity.this);
                SplashActivity.this.finish();
            }
        },sleepTime);

    }
}
