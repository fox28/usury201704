package cn.kkk.usury.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import cn.kkk.usury.view.fragment.CartFragment;
import cn.kkk.usury.view.fragment.HomeFragment;
import cn.kkk.usury.view.fragment.PersonalCenterFragment;

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
    HomeFragment mHomeFragment;
    CartFragment mCartFragment;
    PersonalCenterFragment mPersonalCenterFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initFragment();
        initRadioButton();


    }

    private void initFragment() {
        mFragments = new Fragment[3];
        mHomeFragment = new HomeFragment();
        mCartFragment = new CartFragment();
        mPersonalCenterFragment= new PersonalCenterFragment();
        mFragments[0] = mHomeFragment;
        mFragments[1] = mCartFragment;
        mFragments[2] = mPersonalCenterFragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,mPersonalCenterFragment)
                .add(R.id.fragment_container, mHomeFragment)
                .add(R.id.fragment_container, mCartFragment)
                .show(mHomeFragment)
                .hide(mPersonalCenterFragment).hide(mCartFragment)
                .commit();
    }

    private void initRadioButton() {
        mRadioButtons = new RadioButton[3];
        mRadioButtons[0] = mLayoutHome;
        mRadioButtons[1] = mLayoutCart;
        mRadioButtons[2] = mLayoutPersonalCenter;
    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.layout_home:
                index = 0;
                break;
            case R.id.layout_cart:
                index = 1;
                break;
            case R.id.layout_personal_center:
                index = 2;
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

    @Override
    protected void onResume() {
        super.onResume();
        L.e(TAG, "onResume, index = "+index+", currentIndex = "+ currentIndex);
        setRadioButton();
    }


    // 避免返回到MainActivity后底部button颜色显示不正确
    private void setRadioButton() {
        if (currentIndex >= 0 && currentIndex < mRadioButtons.length) {
            mRadioButtons[currentIndex].setChecked(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
