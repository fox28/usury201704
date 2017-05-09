package cn.kkk.usury.model.net;

import android.content.Context;

import cn.kkk.usury.Application.I;
import cn.kkk.usury.utils.OkHttpUtils;

/**
 * Created by apple on 2017/5/9.
 */

public class UserModel implements IUserModel {
    @Override
    public void loginTemp(Context context, String mac_uuid, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.TEMPORARY_LOGIN)
                .addParam(I.User.MAC_UUID, mac_uuid)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
}
