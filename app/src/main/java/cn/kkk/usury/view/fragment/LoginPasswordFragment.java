package cn.kkk.usury.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.kkk.usury.Application.I;
import cn.kkk.usury.Application.SharePreferenceUtils;
import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.User;
import cn.kkk.usury.utils.L;
import cn.kkk.usury.utils.UserUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by apple on 2017/5/7.
 */

public class LoginPasswordFragment extends Fragment {
    private static final String TAG = "LoginPasswordFragment";

    TextView mTvRegister, mTvPasswordLost;
    EditText mEtPhone, mEtPassword;
    Button mBtnLogin;
    String telephone;
    String password;
    String access_token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_login_password, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtPhone = (EditText) view.findViewById(R.id.et_phone_num);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
        mTvRegister = (TextView) view.findViewById(R.id.tv_register);
        mTvPasswordLost = (TextView) view.findViewById(R.id.tv_password_lost);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        setListener();

    }

    private void initData() {
        SharePreferenceUtils.init(getContext());
        access_token = SharePreferenceUtils.getInstance().getAccessToken();
    }

    private void setListener() {
        setOnClickToRegister();
        setOnClickLoginByPassword();
    }

    private void setOnClickLoginByPassword() {
        // POST：https://modelx.yuzhidushu.com/api/v1/user/login/password
        // Headers: Content-Type:application/json Authorization:Bearer access_token
        // telephone password
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    RequestBody requestBody = new FormBody.Builder()
                            .add(I.LoginByPassword.TELEPHONE, telephone)
                            .add(I.LoginByPassword.PASSWORD, password)
                            .build();
                    Request request = new Request.Builder()
                            .url(I.REQUEST_USER_LOGIN_BY_PASSWORD)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer "+access_token)
                            .post(requestBody)
                            .build();
                    /*L.e(TAG, I.LoginByPassword.TELEPHONE+" = "+telephone+",\n"
                            +I.LoginByPassword.PASSWORD+" = "+password+",\n"
                            +access_token+"\n");*/
                    Call call = new OkHttpClient().newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            L.e(TAG, "setOnClickLoginByPassword, json = "+json);
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                if (jsonObject.getString("errmsg").equals("success")) {
                                    User user = UserUtils.getUserFromJson(jsonObject);

                                    // 使用SharePreferenceUtils给SharePreference的属性赋值
                                    SharePreferenceUtils.init(getContext());
                                    SharePreferenceUtils.getInstance().setId(user.getId());
                                    SharePreferenceUtils.getInstance().setName(user.getName());
                                    SharePreferenceUtils.getInstance().setTelephone(user.getTelephone());
                                    SharePreferenceUtils.getInstance().setAccessToken(user.getAccess_token());
                                    L.e(TAG, "setOnClickLoginByPassword, user = "+user);


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    private void setOnClickToRegister() {
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                L.e(TAG, "setOnClickToRegister, mTvRegister");
                setBroadcastToRegister();
            }
        });
        mTvPasswordLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                L.e(TAG, "setOnClickToRegister, mTvPasswordLost");
                setBroadcastToRegister();
            }
        });
    }


    private void setBroadcastToRegister() {
        Intent intent = new Intent("set-fragment-identifying-code");
        intent.putExtra("index",0);
        getContext().sendBroadcast(intent);
//        L.e(TAG, "setBroadcastToRegister");
    }



    private boolean checkInput() {
        telephone = mEtPhone.getText().toString().trim();
        password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(telephone)) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.telephone_cannot_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mEtPassword.requestFocus();
            mEtPassword.setError(getString(R.string.password_cannot_be_empty));
            return false;
        }
        if (!telephone.matches("\\d{11}")) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.illegal_phone_number));
            return false;
        }
       // 密码不校验正则表达式，提示信息为textPassword，需要解决。
        return true;
    }

}
