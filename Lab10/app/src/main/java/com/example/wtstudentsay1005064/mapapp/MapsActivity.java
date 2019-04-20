package com.example.wtstudentsay1005064.mapapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*
* Name: Atef Yassine
* Date: 04/17/19*/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng EmpireState = new LatLng(40.7484, -73.9857);
        mMap.addMarker(new MarkerOptions().position(EmpireState).title("Empire State Building Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EmpireState));
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
    public void button1(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
    public void button2(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

    }
    public void button3(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }
}
