package edu.gatech.cs2340.rattitudem4;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Creates a Google MapView of rat reports between certain dates
 */
public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
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
        GoogleMap mMap = googleMap;

        Intent intent = getIntent();
        List<RatReport> reports = WelcomePageActivity.dbManager
                .getDateRange(intent.getStringExtra("dateOne"),
                        intent.getStringExtra("dateTwo"));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Log.d("maps"," " + reports.size());

        for (RatReport r : reports) {
            Log.d("MapReport", r.toString());
            LatLng loc;
            if ((r.getLatitude() != null) && (r.getLongitude() != null)) {
                loc = new LatLng(r.getLatitude(), r.getLongitude());
                MarkerOptions marker = new MarkerOptions().position(loc)
                        .title(r.getBorough()).snippet(r.shortToString());
                builder.include(marker.getPosition());
                mMap.addMarker(marker);
            }
        }
        if (reports.size() > 1) {
            LatLngBounds bounds = builder.build();
            final int PADDING = 50;
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, PADDING);
            mMap.animateCamera(cu);
        }
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = myContentsView.findViewById(R.id.title);
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = myContentsView.findViewById(R.id.snippet);
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

    }
}
