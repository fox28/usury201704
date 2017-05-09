package cn.kkk.usury.Application;

import android.app.Application;
import android.content.Context;

import cn.kkk.usury.model.bean.User;

/**
 * Created by apple on 2017/5/9.
 */

public class UsuryApplication extends Application{

    static User currentUser;
    static UsuryApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }

}
