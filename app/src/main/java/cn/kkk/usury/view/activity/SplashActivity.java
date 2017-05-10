package cn.kkk.usury.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.kkk.usury.Application.DeviceUuidFactory;
import cn.kkk.usury.Application.I;
import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.Result;
import cn.kkk.usury.model.bean.User;
import cn.kkk.usury.model.net.IUserModel;
import cn.kkk.usury.model.net.OnCompleteListener;
import cn.kkk.usury.model.net.UserModel;
import cn.kkk.usury.utils.L;
import cn.kkk.usury.utils.MFGT;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by apple on 2017/4/29.
 */

public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";

    private User mUser;
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
        mac_uuid = new DeviceUuidFactory(this).getDeviceUuid().toString();
        L.e(TAG, "initData, mac_uuid = "+mac_uuid);
        model = new UserModel();
        mUser = new User();
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
        // https://modelx.yuzhidushu.com/api/v1/user/temp/login
        // post请求的请求实体
        RequestBody requestBody = new FormBody.Builder()
                .add(I.User.MAC_UUID, mac_uuid)
                .build();
        final Request request = new Request.Builder().url(I.SERVER_ROOT+I.TEMPORARY_LOGIN).post(requestBody).build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    L.e(TAG, "jsonObject = "+jsonObject);
                    if (jsonObject.getString("errmsg").equals("success")) {
                        JSONObject userObject = jsonObject.getJSONObject("data").getJSONObject("user");
                        L.e(TAG, "userObject = "+userObject);
                        L.e(TAG, "id = "+userObject.getInt("id"));
                        mUser.setId(userObject.getInt("id"));
                        mUser.setName(userObject.getString("name"));
                        mUser.setCreated_at(userObject.getString("created_at"));
                        mUser.setUpdated_at(userObject.getString("updated_at"));
                        mUser.setMac_uuid(userObject.getString("mac_uuid"));
                        mUser.setAccess_token(userObject.getString("access_token"));
                        L.e(TAG, "mUser = "+mUser);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                L.e(TAG,"json = " +json);
                Result result = new Gson().fromJson(json, Result.class);
                L.e(TAG, "result = "+result);

            }
        });
    }
}
