package com.vodiy.idealservices.service.subservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.vodiy.idealservices.R;
/**
 * Created by idiyor on 03/01/2016.
 */
public class SubServiceAdapter extends BaseAdapter {

    private List<String> mSubServicesList;
    private Context mContext;

    public SubServiceAdapter(Context context, List<String> subServicesList) {
        mContext = context;
        mSubServicesList = subServicesList;
    }

    @Override
    public int getCount() {
        return mSubServicesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subservice_list_item, parent, false);
            SubServiceRowHolder holder = new SubServiceRowHolder();
            holder.mRowTitleHolder = (TextView) view.findViewById(R.id.rowTitle);
            holder.mRowIconHolder = (ImageView) view.findViewById(R.id.rowIcon);

            view.setTag(holder);
        }

        SubServiceRowHolder holder = (SubServiceRowHolder) view.getTag();
        holder.mRowTitleHolder.setText(mSubServicesList.get(position));
        holder.mRowIconHolder.setImageResource(R.mipmap.nav_icon);

        return view;
    }
}
