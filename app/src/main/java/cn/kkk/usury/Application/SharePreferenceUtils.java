package cn.kkk.usury.Application;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by apple on 2017/4/6.
 */

/**
 * 一般在LoginActivity的loginSuccess()中调用
 */
public class SharePreferenceUtils {
    // sharePreference的文件名, 该文件用于保存username、username可以用于从database读取数据
    private static final String SHARE_PREFERENCE_NAME = "cn.kkk.usury_save_userInfo";
    // 保存数据的字段
    private static final String SAVE_USERINFO_PHONENUM = "m_user_phoneNum";

    static SharePreferenceUtils instance;
    SharedPreferences sharePreferences;
    SharedPreferences.Editor editor;

    public SharePreferenceUtils() {
        sharePreferences = UsuryApplication.getInstance()
                .getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharePreferences.edit();
    }

    public static SharePreferenceUtils getInstance() {
        if (instance == null) {
            instance = new SharePreferenceUtils();
        }
        return instance;
    }

    // 保存数据方法
    // 切记别忘了接commit()
    public void setPhoneNum(String phoneNum) {
        editor.putString(SAVE_USERINFO_PHONENUM, phoneNum).commit();
    }

    // 读取数据方法
    public String getPhoneNum() {
        return sharePreferences.getString(SAVE_USERINFO_PHONENUM, null);
    }

    // 用户退出登录后、清空数据
    public void removeUsername() {
        editor.remove(SAVE_USERINFO_PHONENUM).commit();
    }
}
