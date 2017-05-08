package cn.kkk.usury.view.fragment;

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

/**
 * Created by apple on 2017/5/7.
 */

public class LoginIdentifyingCodeFragment extends Fragment {
    EditText mEtPhone, mEtIdentifyingCode;
    TextView mTvSendCode;
    Button mBtnLogin;

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

        setListener();
    }

    private void setListener() {
        setOnClickLoginByIdentifyingCode();
    }

    private void setOnClickLoginByIdentifyingCode() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });

    }

    private boolean checkInput() {
        String phoneNum = mEtPhone.getText().toString().trim();
        String identifyingCode = mEtIdentifyingCode.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.phoneNum_cannot_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(identifyingCode)) {
            mEtIdentifyingCode.requestFocus();
            mEtIdentifyingCode.setError(getString(R.string.identifyingCode_cannot_be_empty));
        }
        if (!phoneNum.matches("\\d{11}")) {
            mEtPhone.requestFocus();
            mEtPhone.setError(getString(R.string.illegal_phone_number));
        }
        return true;
    }
}
