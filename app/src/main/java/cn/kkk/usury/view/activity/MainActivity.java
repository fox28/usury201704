package cn.kkk.usury.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.kkk.usury.R;
import cn.kkk.usury.utils.L;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.layout_home)
    RadioButton mLayoutHome;
    @BindView(R.id.layout_cart)
    RadioButton mLayoutCart;
    @BindView(R.id.layout_personal_center)
    RadioButton mLayoutPersonalCenter;
    @BindView(R.id.main_bottom)
    RadioGroup mMainBottom;
    @BindView(R.id.fragment_container)
    RelativeLayout mFragmentContainer;
    Unbinder unbinder;

    int index = 0;
    int currentIndex = 0;
    // 别忘了初始化！！！
    RadioButton[] mRadioButtons;
    Fragment[] mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initFragment();
        initRadioButton();

    }

    private void initFragment() {

    }

    private void initRadioButton() {
    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.layout_home:
                break;
            case R.id.layout_cart:
                break;
            case R.id.layout_personal_center:
                break;
        }
        setFragment();
    }

    private void setFragment() {

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        L.e(TAG, "onResume, index = "+index+", currentIndex = "+ currentIndex);
//        setRadioButton();
//    }
//
//
//    // 避免返回到MainActivity后底部button颜色显示不正确
//    private void setRadioButton() {
//        if (currentIndex >= 0 && currentIndex < mRadioButtons.length) {
//            mRadioButtons[currentIndex].setChecked(true);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
