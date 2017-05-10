package cn.kkk.usury.Application;

import android.app.Application;
import android.content.Context;

import cn.kkk.usury.model.bean.User;
import cn.kkk.usury.utils.L;

/**
 * Created by apple on 2017/5/9.
 */

public class UsuryApplication extends Application{

    static User currentUser;
    static UsuryApplication instance;
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = this;
    }

    public static Context getInstance() {
        return instance;
    }

    public static User getCurrentUser() {
        if (currentUser != null) {
            String phoneNum = SharePreferenceUtils.getInstance().getPhoneNum();
            L.e("application", "phoneNum = "+phoneNum);
            currentUser = new User(phoneNum);
        }
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UsuryApplication.currentUser = currentUser;
    }

}
