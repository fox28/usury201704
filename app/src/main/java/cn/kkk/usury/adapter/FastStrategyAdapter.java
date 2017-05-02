package cn.kkk.usury.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.AppBean;

/**
 * Created by apple on 2017/5/2.
 */

public class FastStrategyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<AppBean> arrayList;

    public FastStrategyAdapter(Context context, ArrayList<AppBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_strategy, null);
        return new StrategyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
        AppBean bean = arrayList.get(position);
        StrategyViewHolder holder = (StrategyViewHolder) parentHolder;
        holder.ivThumb.setImageResource(bean.getPhotoId());
        holder.tvAppName.setText(bean.getName());
        holder.tvAppInfo.setText(bean.getIntro());
    }

    @Override
    public int getItemCount() {
        return arrayList!=null?(arrayList.size()>3?3:arrayList.size()):0;
    }

    class StrategyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvAppName, tvAppInfo;
        public StrategyViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.iv_strategy_thumb);
            tvAppName = (TextView) itemView.findViewById(R.id.tv_strategy_appName);
            tvAppInfo = (TextView) itemView.findViewById(R.id.tv_strategy_info);
        }
    }

}
