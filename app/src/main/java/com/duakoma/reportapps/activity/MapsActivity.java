package com.duakoma.reportapps.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.duakoma.reportapps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        LatLng cakung = new LatLng(-6.1854659,106.9010924);
        LatLng stoTelkom = new LatLng(-6.1810366,106.8240497);
        LatLng tlt = new LatLng(-6.230461,106.8157594);
        mMap.setMinZoomPreference(9.0f);
        //mMap.setMaxZoomPreference(14.0f);
        mMap.addMarker(new MarkerOptions().position(cakung).title("Rumah Pertama"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cakung));
        mMap.addMarker(new MarkerOptions().position(stoTelkom).title("Rumah Kedua"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stoTelkom));
        mMap.addMarker(new MarkerOptions().position(tlt).title("Rumah Ketiga"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tlt));

    }
}
