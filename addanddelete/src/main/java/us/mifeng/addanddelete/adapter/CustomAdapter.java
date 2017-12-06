package us.mifeng.addanddelete.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.lankton.flowlayout.FlowLayout;
import us.mifeng.addanddelete.R;
import us.mifeng.addanddelete.bean.Custom;
import us.mifeng.addanddelete.utils.Dip2PxUtils;

/**
 * Created by 黑夜之火 on 2017/8/22.
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private Custom custom;
    private LayoutInflater inflater;
    private ICustomData iCustomData;

    public CustomAdapter(Context context, Custom custom, ICustomData iCustomData) {
        this.context = context;
        this.custom = custom;
        inflater = LayoutInflater.from(context);
        this.iCustomData = iCustomData;
    }

    @Override
    public int getCount() {
        return custom.getD().size();
    }

    @Override
    public Custom.D getItem(int position) {
        return custom.getD().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Custom.D d = getItem(position);
        vh.textView.setText(d.getName());
        initRan(vh.flowLayout, custom.getM().get(position), position);
        return convertView;
    }

    private void initRan(final FlowLayout flowLayout, final ArrayList<Custom.M> ms, final int position) {
        flowLayout.removeAllViews();
        for (int i = 0; i < ms.size(); i++) {
            int ranHeight = Dip2PxUtils.dip2px(context, 30);
            int ranWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ranWidth, ranHeight);
            lp.setMargins(Dip2PxUtils.dip2px(context, 10), 5, Dip2PxUtils.dip2px(context, 10), 5);
            final TextView tv = new TextView(context);
            tv.setPadding(Dip2PxUtils.dip2px(context, 15), 0, Dip2PxUtils.dip2px(context, 15), 0);
            tv.setTextColor(Color.parseColor("#ff3030"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setText(ms.get(i).getName());

            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setLines(1);
            tv.setTextColor(Color.BLACK);
            tv.setBackgroundColor(Color.parseColor("#cccccc"));
            tv.setTag(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCustomData.removeData(ms.get((Integer) tv.getTag()),position);
                }
            });
            flowLayout.addView(tv, lp);
        }

    }

    public class ViewHolder {
        public TextView textView;
        public FlowLayout flowLayout;

        public ViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.textview);
            flowLayout = (FlowLayout) view.findViewById(R.id.flowLayout);
        }

    }

    public interface ICustomData {
        void removeData(Custom.M m,int postion);
    }
}
