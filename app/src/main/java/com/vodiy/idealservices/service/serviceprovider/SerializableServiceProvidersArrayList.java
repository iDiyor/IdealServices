package com.vodiy.idealservices.service.serviceprovider;

import com.vodiy.idealservices.service.serviceprovider.ServiceProvider;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by idiyor on 03/01/2016.
 */
public class SerializableServiceProvidersArrayList implements Serializable {

    private ArrayList<ServiceProvider> mArrayList;

    public SerializableServiceProvidersArrayList(ArrayList<ServiceProvider> arrayList) {
        mArrayList = arrayList;
    }

    public ArrayList<ServiceProvider> getArrayList() {
        if (mArrayList != null) {
            return mArrayList;
        }
        return null;
    }

}
