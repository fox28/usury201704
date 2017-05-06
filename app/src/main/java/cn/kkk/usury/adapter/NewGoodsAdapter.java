package cn.kkk.usury.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.AppBean;

/**
 * Created by apple on 2017/5/2.
 */

public class NewGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<AppBean> arrayList;
    ItemLabelAdapter2 adapter;
    ArrayList<String> tvList = new ArrayList<>();

    public NewGoodsAdapter(Context context, ArrayList<AppBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        tvList.add("身份证");
        tvList.add("支付宝");
        tvList.add("京东");
        tvList.add("分期还款");
        adapter = new ItemLabelAdapter2(context, tvList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_app_new_goods, null);
        return new NewGoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
        AppBean bean = arrayList.get(position);
        NewGoodsViewHolder holder = (NewGoodsViewHolder) parentHolder;
        holder.ivThumb.setImageResource(bean.getPhotoId());
        holder.tvAppName.setText(bean.getName());
        holder.tvAppInfo.setText(bean.getIntro());
    }

    @Override
    public int getItemCount() {
        return arrayList!=null?(arrayList.size()>6?6:arrayList.size()):0;
    }

    class NewGoodsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvAppName, tvAppInfo;
        GridView mGridView;
        public NewGoodsViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            tvAppName = (TextView) itemView.findViewById(R.id.tv_appName);
            tvAppInfo = (TextView) itemView.findViewById(R.id.tv_appIntro);
            mGridView = (GridView) itemView.findViewById(R.id.gv_newGoods);
            mGridView.setAdapter(adapter);
            mGridView.setHorizontalSpacing(0);
        }
    }

}
