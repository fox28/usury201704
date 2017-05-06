package cn.kkk.usury.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.AppBean;

/**
 * Created by apple on 2017/5/2.
 */

public class MarketAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<AppBean> arrayList;
    ArrayList<String> tvList = new ArrayList<>();
    ItemLabelAdapter mItemLabelAdapter;

    public MarketAppAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        tvList.add("身份证");
        tvList.add("淘宝");
        tvList.add("蚂蚁金服");
        mItemLabelAdapter = new ItemLabelAdapter(context, tvList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_market_app, null);
        return new ItemMarketHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
        ItemMarketHolder holder = (ItemMarketHolder) parentHolder;
        AppBean bean = arrayList.get(position);
        holder.tvAppInfo.setText(bean.getIntro());
        holder.tvAppName.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList!=null?arrayList.size():0;
    }

    class ItemMarketHolder extends RecyclerView.ViewHolder {
        TextView tvAppInfo, tvAppName;
        GridView gridView;
        public ItemMarketHolder(View itemView) {
            super(itemView);
            tvAppInfo = (TextView) itemView.findViewById(R.id.tv_market_appInfo);
            tvAppName = (TextView) itemView.findViewById(R.id.tv_appName_market);
            gridView = (GridView) itemView.findViewById(R.id.gv_appLabel_market);



            gridView.setAdapter(mItemLabelAdapter);
        }
    }




}
