package cn.kkk.usury.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.adapter.MarketAppAdapter;
import cn.kkk.usury.model.bean.AppBean;
import cn.kkk.usury.model.dao.AppDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment {
    ArrayList<AppBean> arrayList;
    MarketAppAdapter adapter;
    LinearLayoutManager manager;
    RecyclerView mRecyclerView;

    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_market, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

        // 填充数据
        manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        adapter = new MarketAppAdapter(getContext(), arrayList);
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        arrayList = AppDao.getData(getContext());
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_market);
    }

}
