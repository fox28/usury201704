package cn.kkk.usury.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.kkk.usury.R;
import cn.kkk.usury.view.widget.AdView;

/**
 * Created by apple on 2017/5/13.
 */

public class AdViewAdapter {
    private List<String> mDataList;
//    Context context;

    public AdViewAdapter( List<String> datas) {
        mDataList = datas;
        if (mDataList == null || mDataList.isEmpty()) {
            throw new RuntimeException("显示数据为空");
        }
    }

    /**
     * 获取列表项的个数
     * @return
     */
    public int getCount() {
        return mDataList!=null?mDataList.size():0;
    }

    /**
     * 获取指定位置的列表项
     * @param position
     * @return
     */
    public String getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 获取单个列表项布局
     * @param parent 父容器
     * @return
     */
    public View getView(AdView parent) {
        View view = View.inflate(parent.getContext(), R.layout.ad_item,null);
        return view;
    }

    public void setView(View view, String adTitle) {
        TextView tv = (TextView) view.findViewById(R.id.tv_ad_title);
        tv.setText(adTitle);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "跳转相应页面", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
