package cn.kkk.usury.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import cn.kkk.usury.R;
import cn.kkk.usury.view.activity.LoginActivity;
import cn.kkk.usury.view.activity.MainActivity;


/**
 * Created by apple on 2017/3/30.
 */

public class MFGT {

    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void startActivity(Activity activity, Class cls) {
        activity.startActivity(new Intent(activity, cls));
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);

    }
    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    public static void gotoMainActivity(Activity activity) {
        startActivity(activity, MainActivity.class);
    }

    public static void gotoLogin(Activity activity) {
        startActivity(activity, LoginActivity.class);
    }

//    public static void gotoWelcomeActivity(Activity activity) {
//        startActivity(activity, WelcomeActivity.class);
//    }

//    public static void gotoRegisterActivity(Activity activity) {
//        startActivity(activity, RegisterActivity.class);
//    }
}
