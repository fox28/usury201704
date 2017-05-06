package cn.kkk.usury.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.kkk.usury.R;

/**
 * Created by apple on 2017/5/5.
 */

public class ItemLabelAdapter2 extends BaseAdapter {
    Context context;
    ArrayList<String> arrayList;

    public ItemLabelAdapter2(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList!=null?(arrayList.size()>5?5:arrayList.size()):0;
    }

    @Override
    public String getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_textview2,null);
        TextView tvLabel = (TextView) view.findViewById(R.id.tv_textView);
        tvLabel.setText(arrayList.get(position));
        return view;
    }

}
