package com.vodiy.idealservices.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vodiy.idealservices.R;
import com.vodiy.idealservices.service.serviceprovider.SerializableServiceProvidersArrayList;
import com.vodiy.idealservices.service.serviceprovider.ServiceProviderAdapter;

/**
 * Created by idiyor on 03/01/2016.
 */
public class ServiceProvidersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedBundleInstance) {
        View view = inflater.inflate(R.layout.service_providers_fragment, parent, false);


        ListView listView = (ListView) view.findViewById(R.id.serviceProvidersListView);

        SerializableServiceProvidersArrayList serviceProvidersList = (SerializableServiceProvidersArrayList) getArguments().getSerializable("ServiceProvidersList");


        ServiceProviderAdapter adapter = new ServiceProviderAdapter(getActivity(), serviceProvidersList.getArrayList());
        listView.setAdapter(adapter);



        return view;
    }
}
