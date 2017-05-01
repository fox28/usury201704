package cn.kkk.usury.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.AppBean;

/**
 * Created by apple on 2017/4/30.
 */

public class SortPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<AppBean> arrayList;

    public SortPicAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = View.inflate(context, R.layout.app_item, null);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sort_pic, null);

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
        AppBean bean = arrayList.get(position);
        AppViewHolder holder = (AppViewHolder) parentHolder;
        holder.ivSortPic.setImageResource(bean.getPhotoId());

    }



    @Override
    public int getItemCount() {
        return arrayList !=null? arrayList.size():0;
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSortPic;
        public AppViewHolder(View itemView) {
            super(itemView);
            ivSortPic = (ImageView) itemView.findViewById(R.id.iv_sortPic);
        }
    }
}
