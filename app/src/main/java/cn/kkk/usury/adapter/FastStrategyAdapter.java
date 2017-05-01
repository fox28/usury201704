package cn.kkk.usury.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.kkk.usury.R;
import cn.kkk.usury.model.bean.AppBean;
import cn.kkk.usury.model.bean.AppDetailBean;

/**
 * Created by apple on 2017/4/30.
 */

public class FastStrategyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<AppDetailBean> arrayList;

    public FastStrategyAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = View.inflate(context, R.layout.app_item, null);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_fast_recommend, null);

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
        AppDetailBean bean = arrayList.get(position);
        AppViewHolder holder = (AppViewHolder) parentHolder;


    }



    @Override
    public int getItemCount() {
        return arrayList !=null? arrayList.size():0;
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        public AppViewHolder(View itemView) {
            super(itemView);


        }
    }
}
