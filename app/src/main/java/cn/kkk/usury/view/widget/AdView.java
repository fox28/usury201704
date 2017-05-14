package cn.kkk.usury.view.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import cn.kkk.usury.R;
import cn.kkk.usury.adapter.AdViewAdapter;

/**
 * Created by apple on 2017/5/13.
 */

public class AdView extends LinearLayout {

    // 控件高度
    private float mAdHeight = 0f;
    // 时间间隔
    private final int mGap = 4000;
    // 动画持续时间
    private final int mAnimDuration = 1000;
    // 显示文字的尺寸
    private final float TEXTSIZE = 20f;
    private AdViewAdapter mAdapter;
    private final float AdvertisementHeight = 30;

    // 显示的View
    private View mFirstView;
    private View mSecondView;

    // 播放的下标
    private int mPosition;
    // 线程的标识
    private boolean isStarted;
    // 画笔
    private Paint mPaint;

    public AdView(Context context) {
        this(context, null);
    }

    public AdView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    /**
     * 初始化属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // 设置为垂直方向
        setOrientation(VERTICAL);
        // 画笔实例化，并设置抗锯齿效果
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 获得自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AdView);
        mAdHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AdvertisementHeight,getResources().getDisplayMetrics());
        int gap = array.getInteger(R.styleable.AdView_gap, mGap);
        int animDuration = array.getInteger(R.styleable.AdView_animDuration, mAnimDuration);

        if (mGap <= mAnimDuration) {
            gap = mGap;
            animDuration = mAnimDuration;
        }

        // 关闭清空TypeArray
        array.recycle();
    }

    /**
     * 设置数据
     * @param adapter 适配器
     */
    public void setAdapter(AdViewAdapter adapter) {
        this.mAdapter = adapter;
        setUpAdapter();
    }

    /**
     * 设置s数据适配
     */
    private void setUpAdapter() {
        // 移除所有view
        removeAllViews();
        // 如果只有一条数据，不垂直滚动
        if (mAdapter.getCount() == 1) {
            mFirstView = mAdapter.getView(this);
            mAdapter.setView(mFirstView, mAdapter.getItem(0));
            addView(mFirstView);
        } else {// 多个数据
            mFirstView = mAdapter.getView(this);
            mSecondView = mAdapter.getView(this);
            mAdapter.setView(mFirstView, mAdapter.getItem(0));
            mAdapter.setView(mSecondView, mAdapter.getItem(1));

            //把两个添加到控件里面
            addView(mFirstView);
            addView(mSecondView);
            mPosition = 1;
            isStarted = false;

        }
    }

    private AnimRunnable mRunnable = new AnimRunnable();
    private class AnimRunnable implements Runnable{
        @Override
        public void run() {
            performSwitch();
            postDelayed(this, mGap);
        }

    }
    /**
     * 垂直滚动
     */
    private void performSwitch() {

        // 属性动画控制控件滚动，y轴方向滚动
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mFirstView, "translationY",
                mFirstView.getTranslationY()-mAdHeight);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mSecondView, "translationY",
                mSecondView.getTranslationY()-mAdHeight);

        // 动画集
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2);// 两个属性动画放在一起
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {// 动画结束
                super.onAnimationEnd(animation);
                mFirstView.setTranslationY(0);
                mSecondView.setTranslationY(0);

                View removeView = getChildAt(0);// 获得第一个子布局
                mPosition++;
                //设置显示的布局
                mAdapter.setView(removeView,mAdapter.getItem(mPosition%mAdapter.getCount()));

                removeView(removeView);// 移除前一个view
                addView(removeView,1);
            }
        });
        set.setDuration(mAnimDuration);// 设置动画持续时间
        set.start();// 开启动画

    }

    /**
     * 开启线程
     */
    public void start() {
        if (!isStarted && mAdapter.getCount() > 1) {
            isStarted = true;
            postDelayed(mRunnable, mGap); // 间隔mGap刷新一次UI
        }
    }

    /**
     * 暂停滚动
     */
    public void stop() {
        // 移除handle更新
        removeCallbacks(mRunnable);
        isStarted = false;// 暂停线程
    }

    /**
     * 测量控件的宽、高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (LayoutParams.WRAP_CONTENT == getLayoutParams().height) {
            getLayoutParams().height = (int) mAdHeight;
        } else {
            mAdHeight = getHeight();
        }
        if (mFirstView != null) {
            mFirstView.getLayoutParams().height = (int) mAdHeight;
        }
        if (mSecondView != null) {
            mSecondView.getLayoutParams().height = (int) mAdHeight;
        }
    }

    /**
     * 画布局
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, TEXTSIZE,
                getResources().getDisplayMetrics()));
        mPaint.setStyle(Paint.Style.STROKE);// 设置画出图像是空心的
        canvas.drawText("瑞士军刀", TEXTSIZE, getHeight()*2/3, mPaint);// 写文字
    }

    /**
     * 销毁view时调用
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 停止滚动
        stop();
    }

    /**
     * 屏幕旋转
     * @param newConfig
     */
    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
