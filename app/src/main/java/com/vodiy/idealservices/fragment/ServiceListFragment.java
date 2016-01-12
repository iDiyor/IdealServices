package com.vodiy.idealservices.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vodiy.idealservices.DataProvider;
import com.vodiy.idealservices.R;
import com.vodiy.idealservices.service.serviceprovider.SerializableServiceProvidersArrayList;
import com.vodiy.idealservices.service.serviceprovider.ServiceProvider;
import com.vodiy.idealservices.service.Services;
import com.vodiy.idealservices.service.subservice.SubServiceAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by idiyor on 03/01/2016.
 */
public class ServiceListFragment extends Fragment {

    private ArrayList<String> mSubServicesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.service_list_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.subServicesListView);

        mSubServicesList = new ArrayList<>();

        String selectedServiceName = getArguments().getString("ServiceName");


        final DataProvider dataProvider = new DataProvider();
        InputStream inputStream = getResources().openRawResource(R.raw.database);
        dataProvider.parseData(inputStream);


        HashMap<String, List<String>> serviceAndSubServiceMap = dataProvider.getParentAndChildServicesList();

        for (Map.Entry<String, List<String>> entry : serviceAndSubServiceMap.entrySet()) {
            String serviceName = entry.getKey();
            if (serviceName.equals(selectedServiceName)) {
                List<String> subServicesList = (ArrayList<String>) entry.getValue();
                for (String subService : subServicesList) {
                    Log.d("TAG", serviceName + " : " + subService);
                    mSubServicesList.add(subService);
                }
            }
        }

        SubServiceAdapter adapter = new SubServiceAdapter(getActivity(), mSubServicesList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String subServiceName = mSubServicesList.get(position);
                ArrayList<ServiceProvider> serviceProvidersList = dataProvider.getServiceProvidersListForSubService(subServiceName);

                // Create new fragment and transaction
                ServiceProvidersFragment newFragment = new ServiceProvidersFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                Bundle args = new Bundle();
                args.putSerializable("ServiceProvidersList", new SerializableServiceProvidersArrayList(serviceProvidersList));

                newFragment.setArguments(args);

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });


        return view;

    }
}
