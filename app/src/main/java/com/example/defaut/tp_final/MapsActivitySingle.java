package com.example.defaut.tp_final;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivitySingle extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activity_single);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        intent = getIntent();




        //final TextView monTextVue = (TextView) findViewById(R.id.text);

        //monTextVue.setText(intent.getStringExtra("mag") + " " + intent.getStringExtra("place") + " " + intent.getStringExtra("time"));
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

        LatLng seisme = new LatLng(new Double(intent.getStringExtra("latitude")), new Double(intent.getStringExtra("longitude")));


        MarkerOptions marker = new MarkerOptions();

        marker.position(seisme);
        marker.title("Magnitude : "+ intent.getStringExtra("mag"));
        marker.snippet("Depth : " + intent.getStringExtra("depth") + " Time : " + intent.getStringExtra("time"));

        mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(seisme));
    }
}
