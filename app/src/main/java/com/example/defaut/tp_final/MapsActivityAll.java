package com.example.defaut.tp_final;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivityAll extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activity_all);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intent = getIntent();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        ArrayList<MarkerOptions> listeMarker = new ArrayList<MarkerOptions>();


        Bundle extras = intent.getExtras();

        int nbElement = new Integer(intent.getStringExtra("size"));

        ArrayList<String > listeMag = extras.getStringArrayList("mag");
        ArrayList<String > listePlace = extras.getStringArrayList("place");
        ArrayList<String > listeTime = extras.getStringArrayList("time");

        ArrayList<String > listeLong = extras.getStringArrayList("longitude");
        ArrayList<String > listeLat = extras.getStringArrayList("latitude");
        ArrayList<String > listeDeph = extras.getStringArrayList("depth");


        for (int i=0; i<nbElement; i++){
            MarkerOptions marker = new MarkerOptions();

            // extras.getString("myKey")

            LatLng pos = new LatLng(Double.parseDouble(listeLat.get(i)), Double.parseDouble(listeLong.get(i)));



            marker.position(pos);
            marker.title("Magnitude : "+ listeMag.get(i));
            marker.snippet("Depth : " + listeDeph.get(i) + " Time : " + listeTime.get(i));


            listeMarker.add(marker);
        }


        for (int i=0; i<listeMarker.size(); i++){
            mMap.addMarker(listeMarker.get(i));
        }





    }
}
