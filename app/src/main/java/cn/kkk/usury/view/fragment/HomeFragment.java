package cn.kkk.usury.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cn.kkk.usury.Application.I;
import cn.kkk.usury.Application.SharePreferenceUtils;
import cn.kkk.usury.R;
import cn.kkk.usury.adapter.AdViewAdapter;
import cn.kkk.usury.adapter.FastRecommendAdapter;
import cn.kkk.usury.adapter.FastStrategyAdapter;
import cn.kkk.usury.adapter.NewGoodsAdapter;
import cn.kkk.usury.adapter.SortPicAdapter;
import cn.kkk.usury.adapter.SpecialOffersAdapter;
import cn.kkk.usury.model.bean.AppBean;
import cn.kkk.usury.model.bean.SlideBean;
import cn.kkk.usury.model.dao.AppDao;
import cn.kkk.usury.utils.L;
import cn.kkk.usury.view.widget.AdView;
import cn.kkk.usury.view.widget.FlowIndicator;
import cn.kkk.usury.view.widget.SlideLoopView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    RecyclerView mRV_SortPic, mRV_fastRecommend, mRV_fastStrategy, mRV_SpecialOffers, mRV_NewGoods;
    StaggeredGridLayoutManager manager;
    LinearLayoutManager linearManager;
    SortPicAdapter mSortPicAdapter;
    FastRecommendAdapter mFastRecommendAdapter;
    FastStrategyAdapter mFastStrategyAdapter;
    SpecialOffersAdapter mSpecialOffersAdapter;
    NewGoodsAdapter mNewGoodsAdapter;
    ArrayList<AppBean> mArrayList;

    SlideLoopView mSlideLoopView;
    FlowIndicator mFlowIndicator;
    ArrayList<String> mSlideList;

    // 垂直轮播数据
    ArrayList<String> mNewList;
    AdView mAdView;

    String access_token;
    OkHttpClient mOkHttpClient;

    // 图片轮播数据
    ArrayList<SlideBean> mSlideBeenList;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mOkHttpClient = new OkHttpClient();
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        initData();

        // 轮播图
        mSlideLoopView.startPlay(getContext(),mFlowIndicator,mSlideList);

        // 垂直轮播图
        AdViewAdapter mAdAdapter = new AdViewAdapter(mNewList);
        mAdView.setAdapter(mAdAdapter);
        mAdView.start();

        // 8张分类图片
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRV_SortPic.setLayoutManager(manager);
        mRV_SortPic.setHasFixedSize(true);
        mSortPicAdapter = new SortPicAdapter(getContext(), mArrayList);
        mRV_SortPic.setAdapter(mSortPicAdapter);

        // 速贷推荐
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        mRV_fastRecommend.setLayoutManager(manager);
        mRV_SortPic.setHasFixedSize(true);
        mFastRecommendAdapter = new FastRecommendAdapter(getContext(), mArrayList);
        mRV_fastRecommend.setAdapter(mFastRecommendAdapter);

        // 速贷攻略
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRV_fastStrategy.setLayoutManager(manager);
        mRV_fastStrategy.setHasFixedSize(true);
        mFastStrategyAdapter = new FastStrategyAdapter(getContext(), mArrayList);
        mRV_fastStrategy.setAdapter(mFastStrategyAdapter);

        // 优惠活动
        linearManager = new LinearLayoutManager(getContext());
        mRV_SpecialOffers.setLayoutManager(linearManager);
        mRV_SpecialOffers.setHasFixedSize(true);
        mSpecialOffersAdapter = new SpecialOffersAdapter(getContext(), mArrayList);
        mRV_SpecialOffers.setAdapter(mSpecialOffersAdapter);

        // 新产品上线
        linearManager = new LinearLayoutManager(getContext());
        mRV_NewGoods.setLayoutManager(linearManager);
        mRV_NewGoods.setHasFixedSize(true);
        mNewGoodsAdapter = new NewGoodsAdapter(getContext(), mArrayList);
        mRV_NewGoods.setAdapter(mNewGoodsAdapter);


    }

    private void initView(View view) {
        mRV_SortPic = (RecyclerView) view.findViewById(R.id.RV_LoanSortPicture);
        mRV_fastRecommend = (RecyclerView) view.findViewById(R.id.RV_fastRecommend);
        mRV_fastStrategy = (RecyclerView) view.findViewById(R.id.RV_fastStrategy);
        mRV_SpecialOffers = (RecyclerView) view.findViewById(R.id.RV_specialOffers);
        mRV_NewGoods = (RecyclerView) view.findViewById(R.id.RV_NewGoods);

        mSlideLoopView = (SlideLoopView) view.findViewById(R.id.slv_homeFragment);
        mFlowIndicator = (FlowIndicator) view.findViewById(R.id.flowIndicator_homeFragment);

        // 垂直轮播
        mAdView = (AdView) view.findViewById(R.id.adView_vertical_loop);
    }

    private void initData() {
        mArrayList = AppDao.getData(getContext());

        mSlideList = new ArrayList<>();
        mSlideList.add("goods01.png");
        mSlideList.add("goods02.png");
        mSlideList.add("goods03.png");
        mSlideList.add("goods04.png");
        mSlideList.add("goods05.png");

        mNewList = new ArrayList<>();
        mNewList.add("瑞士军刀，满200-50");
        mNewList.add("家居家装换新季， 满199减100");
        mNewList.add("带上相机去旅游，尼康低至477");
        mNewList.add("价格惊呆！电信千兆光纤上市");

        mSlideBeenList = new ArrayList<>();

        initAccessToken();
        initJsonByLogin();


    }

    private void initJsonByLogin() {
        // https://modelx.yuzhidushu.com/api/v1/homepage
        // Headers Content-Type:application/json Authorization:Bearer access_token
        Request request = new Request.Builder()
                .url(I.REQUEST_HOMEPAGE)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+access_token)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                L.e(TAG, "initJsonByLogin, json = "+json);
                // 获得轮播数据
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getString("errmsg").equals("success")) {
                        JSONArray slideJSArray = jsonObject.getJSONObject("data").getJSONObject("home_page").getJSONArray("slides");
                        L.e(TAG, "slideJSArray = "+slideJSArray);
                        ArrayList<String> urls = new ArrayList<String>();
                        for (int i=0;i<slideJSArray.length();i++) {
                            urls.add(slideJSArray.getJSONObject(i).getString("slide_img_url"));
                            SlideBean bean = new SlideBean();
                            bean.setId(slideJSArray.getJSONObject(i).getInt("slide_id"));
                            bean.setUrl(slideJSArray.getJSONObject(i).getString("slide_img_url"));
                            bean.setDesc(slideJSArray.getJSONObject(i).getString("slide_desc"));
                            mSlideBeenList.add(bean);
                        }
                        L.e(TAG, "urls = " + urls);
//                        mSlideList = urls;
//                        L.e(TAG, "mSlideList = "+mSlideList);
                        L.e(TAG, "mSlideBeanList = "+mSlideBeenList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initAccessToken() {
        SharePreferenceUtils.init(getContext());
        access_token = SharePreferenceUtils.getInstance().getAccessToken();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
