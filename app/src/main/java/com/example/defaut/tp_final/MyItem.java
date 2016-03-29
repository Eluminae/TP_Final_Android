package com.example.defaut.tp_final;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by eluminae on 29/03/16.
 */
public class MyItem /*implements ClusterItem*/ {
    private final LatLng mPosition;

    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}