package cn.kkk.usury.view.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.kkk.usury.R;
import cn.kkk.usury.utils.OkImageLoader;


/**
 * Created by yao on 2017/3/3.
 */

public class SlideLoopView extends ViewPager {
    private static String ROOT_URL="http://10.0.2.2/";


    FlowIndicator mFlowIndicator;
    Handler mHandler;
    GoodsAdapter mAdapter;

    int mCount;
    Timer mTimer;

    public SlideLoopView(Context context) {
        super(context);
    }

    public SlideLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHandler();
        setListener();
    }

    private void setListener() {
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mFlowIndicator.setFocus(position%mCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                setCurrentItem(getCurrentItem()+1);
            }
        };
    }

    class GoodsAdapter extends PagerAdapter {
        Context context;
        ArrayList<String> mGoodsList;

        public GoodsAdapter(Context context, ArrayList<String> mGoodsList) {
            this.context = context;
            this.mGoodsList = mGoodsList;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView ivGoods = new ImageView(context);
            container.addView(ivGoods);
            OkImageLoader.build(ROOT_URL+mGoodsList.get(position%mGoodsList.size()))
                    .width(160)
                    .height(400)
                    .defaultPicture(R.drawable.nopic)
                    .imageView(ivGoods)
                    .showImage(context);
            return ivGoods;
        }
    }

    public void startPlay(Context context, FlowIndicator flowIndicator, ArrayList<String> goodsList) {
        mCount=goodsList.size();
        mFlowIndicator=flowIndicator;
        mFlowIndicator.setCount(goodsList.size());
        mFlowIndicator.setFocus(0);

        mAdapter = new GoodsAdapter(context, goodsList);
        this.setAdapter(mAdapter);

        try {
            MyScroller scroller = new MyScroller(context);
            scroller.setDuration(2000);
            Field filed = ViewPager.class.getDeclaredField("mScroller");
            filed.setAccessible(true);
            filed.set(this,scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        },0,2000);
    }

    public void stopPlay() {
        if (mTimer == null) {
            return;
        }
        mTimer.cancel();
    }

    class MyScroller extends Scroller {
        private int duration;

        public void setDuration(int duration) {
            this.duration=duration;
        }
        public MyScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, this.duration);
        }
    }
}
