package cn.kkk.usury.model.net;

import android.content.Context;

import cn.kkk.usury.utils.OkHttpUtils;

/**
 * Created by apple on 2017/5/9.
 */

public interface IUserModel {
    void loginTemp(Context context, String mac_uuid, OkHttpUtils.OnCompleteListener<String> listener);
}
