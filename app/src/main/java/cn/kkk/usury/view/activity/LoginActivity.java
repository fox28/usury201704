package cn.kkk.usury.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import cn.kkk.usury.R;
import cn.kkk.usury.utils.L;
import cn.kkk.usury.utils.MFGT;
import cn.kkk.usury.view.fragment.LoginIdentifyingCodeFragment;
import cn.kkk.usury.view.fragment.LoginPasswordFragment;


/**
 * Created by apple on 2017/5/7.
 */

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";
    RadioButton mRadioButtonCode, mRadioButtonPassword;
    RadioButton[] mRadioButtons;
    Fragment[] mFragments;
    LoginIdentifyingCodeFragment mCodeFragment;
    LoginPasswordFragment mPasswordFragment;
    RelativeLayout mFragmentContainer;
    LinearLayout mBackArea;
    int index = 0;
    int currentIndex = 0;
    FragmentReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initFragment();
        setListener();

        // 接受广播
        mReceiver = new FragmentReceiver();
        IntentFilter filter = new IntentFilter("set-fragment-identifying-code");
        registerReceiver(mReceiver, filter);

    }

    private void setListener() {
        setOnListenerBack();
    }

    private void setOnListenerBack() {
        mBackArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.finish(LoginActivity.this);
            }
        });
    }

    private void initFragment() {
        mCodeFragment = new LoginIdentifyingCodeFragment();
        mPasswordFragment = new LoginPasswordFragment();
        mFragments = new Fragment[2];
        mFragments[0] = mCodeFragment;
        mFragments[1] = mPasswordFragment;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mCodeFragment)
                .add(R.id.fragment_container, mPasswordFragment)
                .hide(mPasswordFragment)
                .show(mCodeFragment)
                .commit();
    }

    private void initView() {
        mRadioButtonCode = (RadioButton) findViewById(R.id.menu_code);
        mRadioButtonPassword = (RadioButton) findViewById(R.id.menu_password);
        mFragmentContainer = (RelativeLayout) findViewById(R.id.fragment_container);
        mBackArea = (LinearLayout) findViewById(R.id.layout_backArea);
    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.menu_code:
                index = 0;
                break;
            case R.id.menu_password:
                index = 1;
                break;
        }
        L.e(TAG, "index="+index);
        setFragment();
    }

    private void setFragment() {
        if (index != currentIndex) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mFragments[currentIndex]);
            if (!mFragments[index].isAdded()) {
                transaction.add(R.id.fragment_container, mFragments[index]);
            }
            transaction.show(mFragments[index]).commit();
            currentIndex = index;
        }
    }

    class FragmentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            index = intent.getIntExtra("index", index);
            setFragment();
            L.e(TAG, "FragmentReceiver, onReceive, index ="+ index);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
