package practicas.cursoandroid.practicasandroid.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import practicas.cursoandroid.practicasandroid.R;

public class GoogleMapsActivity extends FragmentActivity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.frag_google_maps);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                try {
                    map.setMyLocationEnabled(true);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                map.getUiSettings().setZoomControlsEnabled(true);
                map.getUiSettings().setCompassEnabled(true);
                map.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(new LatLng(40.43, -3.66), 15.0f));
                map.addMarker(new MarkerOptions().title("Estamos aqui")
                        .position(new LatLng(40.43, -3.66)));
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        map.addMarker(new MarkerOptions().title("Click").position(latLng)
                                .icon(BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_YELLOW)));
                    }
                });
            }
        });
    }
}