package cn.kkk.usury.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.kkk.usury.Application.SharePreferenceUtils;
import cn.kkk.usury.R;
import cn.kkk.usury.utils.MFGT;


public class PersonalActivity extends AppCompatActivity {

    TextView mTvTitle, mTvTelephone, mTvUsername;
    LinearLayout mLayoutBack;
    String telephone, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initData();
        initView();
        setOnListener();
    }

    private void initData() {
        SharePreferenceUtils.init(this);
        telephone = SharePreferenceUtils.getInstance().getTelephone();
        name = SharePreferenceUtils.getInstance().getName();
    }

    private void setOnListener() {
        mLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.finish(PersonalActivity.this);
            }
        });
    }

    private void initView() {
        mLayoutBack = (LinearLayout) findViewById(R.id.layout_backArea);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTelephone = (TextView) findViewById(R.id.tv_telephone);
        mTvUsername = (TextView) findViewById(R.id.tv_username);

        mTvTitle.setText(getString(R.string.personal_info));
        mTvTelephone.setText(telephone);
        mTvUsername.setText(name);
    }

    public void onUpdate(View view) {
        MFGT.gotUpdate(PersonalActivity.this);
    }

    public void onQuit(View view) {
        SharePreferenceUtils.getInstance().removeUserInfo();
        finish();
    }
}
