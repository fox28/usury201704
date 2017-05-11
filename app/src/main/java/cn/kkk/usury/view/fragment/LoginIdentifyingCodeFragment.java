package cn.kkk.usury.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import cn.kkk.usury.R;
import cn.kkk.usury.utils.L;
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

public class LoginIdentifyingCodeFragment extends Fragment {
    private static final String TAG = "LoginIdentifyingCodeFra";

    EditText mEtPhone, mEtIdentifyingCode;
    TextView mTvSendCode;
    Button mBtnLogin;
    String key;
    int tickets;
    String telephone;
    String access_token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_login_idencifying_code,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mEtIdentifyingCode = (EditText) view.findViewById(R.id.et_identifying_code);
        mEtPhone = (EditText) view.findViewById(R.id.et_phone_num);
        mTvSendCode = (TextView) view.findViewById(R.id.tv_send_code);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gitTicketsForCode();
        setListener();
    }

    private void gitTicketsForCode() {
        iniAccessToken();

        // https://modelx.yuzhidushu.com/api/v1/user/tickets
        RequestBody requestBody = new FormBody.Builder()
                .add(I.User.TOKEN, I.User.INEEDTICKETS)
                .build();
        Request request = new Request.Builder()
                .url(I.REQUEST_USER_TICKETS)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+access_token)
                .post(requestBody)
                .build();
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
                    JSONObject tokenObject = jsonObject.getJSONObject("data").getJSONObject("temp_token");
//                    L.e(TAG, "tokenObject = "+ tokenObject);
                    key = tokenObject.getString("key");
                    tickets = tokenObject.getInt("tickets");
//                    L.e(TAG, "gitTicketsForCode--onResponse--,key="+key+", tickets="+tickets);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void iniAccessToken() {
        // 获得token
        SharedPreferences sp = getActivity().getSharedPreferences(I.SharePreference.SHARE_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        access_token = sp.getString(I.SharePreference.SAVE_ACCESS_TOKEN, null);
    }


    private void setListener() {
        setOnListenerSendCode();
        setOnListenerLoginByIdentifyingCode();
    }

    private void setOnListenerSendCode() {
        // POST：https://modelx.yuzhidushu.com/api/v1/user/sms
        // 参数 telephone key tickets

        mTvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputForCode()) {
                    RequestBody requestBody = new FormBody.Builder()
                            .add(I.Sms.TELEPHONE, telephone)
                            .add(I.Sms.KEY, key)
                            .add(I.Sms.TICKETS, String.valueOf(tickets))
                            .build();
                    Request request = new Request.Builder()
                            .url(I.REQUEST_USER_SMS)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer "+access_token)
                            .post(requestBody)
                            .build();
                    Call call = new  OkHttpClient().newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            L.e(TAG, "setOnListenerSendCode, onResponse, json = "+json);
                        }
                    });
                }
            }
        });
    }

    private void setOnListenerLoginByIdentifyingCode() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputForLogin();
            }
        });

    }

    private boolean checkInputForCode() {
        telephone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(telephone)) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.telephone_cannot_be_empty));
            return false;
        }
        if (!telephone.matches("\\d{11}")) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.illegal_phone_number));
        }
        return true;
    }

    private boolean checkInputForLogin() {
        telephone = mEtPhone.getText().toString().trim();
        String identifyingCode = mEtIdentifyingCode.getText().toString().trim();
        if (TextUtils.isEmpty(telephone)) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.telephone_cannot_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(identifyingCode)) {
            mEtIdentifyingCode.requestFocus();
            mEtIdentifyingCode.setError(getString(R.string.identifyingCode_cannot_be_empty));
        }
        if (!telephone.matches("\\d{11}")) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.illegal_phone_number));
        }
        return true;
    }
}
