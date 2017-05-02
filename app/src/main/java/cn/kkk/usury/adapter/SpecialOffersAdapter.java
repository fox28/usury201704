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

public class SpecialOffersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<AppBean> arrayList;

    public SpecialOffersAdapter(Context context, ArrayList<AppBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_special_offer, null);
        return new SpecialOffersHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
        AppBean bean = arrayList.get(position);
        SpecialOffersHolder holder = (SpecialOffersHolder) parentHolder;
        holder.ivThumb.setImageResource(bean.getPhotoId());

    }

    @Override
    public int getItemCount() {
        return arrayList!=null?(arrayList.size()>3?3:arrayList.size()):0;
    }

    class SpecialOffersHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        public SpecialOffersHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.iv_special_offer);

        }
    }

}
