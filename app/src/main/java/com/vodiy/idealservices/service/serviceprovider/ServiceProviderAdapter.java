package com.vodiy.idealservices.service.serviceprovider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vodiy.idealservices.R;

import java.util.List;

/**
 * Created by idiyor on 03/01/2016.
 */
public class ServiceProviderAdapter extends BaseAdapter{


    private Context mContext;
    private List<ServiceProvider> mServiceProvidersList;

    public ServiceProviderAdapter(Context context, List<ServiceProvider> serviceProvidersList) {
        mContext = context;
        mServiceProvidersList = serviceProvidersList;
    }

    @Override
    public int getCount() {
        return mServiceProvidersList.size();
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
            view = inflater.inflate(R.layout.service_providers_list_item, parent, false);

            ServiceProviderRowHolder holder = new ServiceProviderRowHolder();
            holder.mProfileImage = (ImageView) view.findViewById(R.id.rowIcon);
            holder.mHeadline = (TextView) view.findViewById(R.id.rowHeadline);
            holder.mText = (TextView) view.findViewById(R.id.rowText);
            holder.mPhone = (TextView) view.findViewById(R.id.rowPhone);

            view.setTag(holder);
        }

        ServiceProviderRowHolder holder = (ServiceProviderRowHolder) view.getTag();

        ServiceProvider serviceProviderItem = mServiceProvidersList.get(position);

        int id = mContext.getResources().getIdentifier(serviceProviderItem.getmProfilePicture(), "mipmap", mContext.getPackageName());
        Drawable drawable = mContext.getResources().getDrawable(id, null);
        holder.mProfileImage.setImageDrawable(drawable);
        holder.mHeadline.setText(serviceProviderItem.getmName());
        holder.mText.setText(String.format("%s: %s", mContext.getResources().getString(R.string.SKILLS_PLACEHOLDER), serviceProviderItem.getmSkills()));
        holder.mPhone.setText(String.format("%s: %s", mContext.getResources().getString(R.string.TELEPHONE_PLACEHOLDER),serviceProviderItem.getmPhone()));


        return view;
    }
}
