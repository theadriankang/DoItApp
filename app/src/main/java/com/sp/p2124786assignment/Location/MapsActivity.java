package com.sp.p2124786assignment.Location;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sp.p2124786assignment.R;
import com.sp.p2124786assignment.databinding.MapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat;
    private double lon;
    private String entryName;
    private double myLat;
    private double myLon;
    private LatLng SAVEDLOCATION;
    private LatLng ME;
    private MapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lat = getIntent().getDoubleExtra("LATITUDE", 0);
        lon = getIntent().getDoubleExtra("LONGITUDE", 0);
        entryName = getIntent().getStringExtra("NAME");
        myLat = getIntent().getDoubleExtra("MYLATITUDE", 0);
        myLon = getIntent().getDoubleExtra("MYLONGITUDE", 0);


        binding = MapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        SAVEDLOCATION = new LatLng(lat,lon);
        ME = new LatLng(myLat,myLon);

        Marker restaurant = mMap.addMarker(new MarkerOptions().position(SAVEDLOCATION).title(entryName));
        Marker me = mMap.addMarker(new MarkerOptions().position(ME).title("ME").snippet("My location").icon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SAVEDLOCATION, 15));
    }
}