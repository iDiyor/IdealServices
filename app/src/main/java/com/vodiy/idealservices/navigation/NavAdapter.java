package com.vodiy.idealservices.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vodiy.idealservices.R;

/**
 * Created by idiyor on 02/01/2016.
 */
public class NavAdapter extends BaseAdapter {

    private String[] mNavTitles;
    private int[] mNavIcons;

    private Context mContext;

    public NavAdapter(Context context, String[] navTitles, int[] navIcons) {
        mContext = context;

        mNavTitles = navTitles;
        mNavIcons = navIcons;
    }


    @Override
    public int getCount() {
        return mNavTitles.length + 1;
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
        if (position == 0) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_list_header_item, parent, false);
            }
        } else {
            position -= 1;

            if (view == null) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_list_item, parent, false);
                NavRowHolder holder = new NavRowHolder();
                holder.mRowTitleHolder = (TextView) view.findViewById(R.id.rowTitle);
                holder.mRowIconHolder = (ImageView) view.findViewById(R.id.rowIcon);

                view.setTag(holder);
            }

            NavRowHolder holder = (NavRowHolder) view.getTag();
            holder.mRowTitleHolder.setText(mNavTitles[position]);
            holder.mRowIconHolder.setImageResource(mNavIcons[position]);
        }


        return view;
    }
}
