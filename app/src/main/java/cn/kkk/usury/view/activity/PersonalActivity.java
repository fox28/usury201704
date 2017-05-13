package cn.kkk.usury.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.kkk.usury.R;
import cn.kkk.usury.utils.MFGT;


public class PersonalActivity extends AppCompatActivity {

    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(getString(R.string.personal_info));
    }

    public void onUpdate(View view) {
        MFGT.gotUpdate(PersonalActivity.this);
    }
}
