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

import cn.kkk.usury.R;
import cn.kkk.usury.utils.L;

/**
 * Created by apple on 2017/5/7.
 */

public class LoginPasswordFragment extends Fragment {
    private static final String TAG = "LoginPasswordFragment";

    TextView mTvRegister, mTvPasswordLost;
    EditText mEtPhone, mEtPassword;
    Button mBtnLogin;
    String phoneNum;
    String password;

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

        setListener();

    }

    private void setListener() {
        setOnClickToRegister();
        setOnClickLoginByPassword();
    }

    private void setOnClickLoginByPassword() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e(TAG, "setOnClickLoginByPassword");
                checkInput();
            }
        });
    }

    private void setOnClickToRegister() {
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e(TAG, "setOnClickToRegister, mTvRegister");
                setBroadcastToRegister();
            }
        });
        mTvPasswordLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e(TAG, "setOnClickToRegister, mTvPasswordLost");
                setBroadcastToRegister();
            }
        });
    }


    private void setBroadcastToRegister() {
        Intent intent = new Intent("set-fragment-identifying-code");
        intent.putExtra("index",0);
        getContext().sendBroadcast(intent);
        L.e(TAG, "setBroadcastToRegister");
    }



    private boolean checkInput() {
        phoneNum = mEtPhone.getText().toString().trim();
        password = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.telephone_cannot_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mEtPassword.requestFocus();
            mEtPassword.setError(getString(R.string.password_cannot_be_empty));
            return false;
        }
        if (!phoneNum.matches("\\d{11}")) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.illegal_phone_number));
            return false;
        }
       // 密码不校验正则表达式，提示信息为textPassword，需要解决。
        return true;
    }

}
