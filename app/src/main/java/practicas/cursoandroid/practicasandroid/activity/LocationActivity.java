package practicas.cursoandroid.practicasandroid.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.service.LocationProviderService;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    private LocationManager manager;
    private TextView positions;
    private Spinner providers;
    private Button on;
    private Button off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        on = (Button) findViewById(R.id.btn_location_on);
        off = (Button) findViewById(R.id.btn_location_off);
        positions = (TextView) findViewById(R.id.txt_location_position);
        providers = (Spinner) findViewById(R.id.spin_location_providers);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> allProviders = manager.getAllProviders();
        providers.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                allProviders
        ));
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        String bestProvider = manager.getBestProvider(criteria, true);
        positions.setText(String.format("Mejor proveedor: %s", bestProvider));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readPosition();
                on.setEnabled(false);
                off.setEnabled(true);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void on(View view) {
        int permission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    1000
            );
        } else {
            readPosition();
            on.setEnabled(false);
            off.setEnabled(true);
        }
    }

    private void readPosition() {
        try {
            String provider = (String) providers.getSelectedItem();
            Location lastKnownLocation = manager.getLastKnownLocation(provider);
            if (lastKnownLocation != null) {

            }
            manager.requestLocationUpdates(provider, 1000, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void off(View view) {
        try {
            manager.removeUpdates(this);
            on.setEnabled(true);
            off.setEnabled(false);
        }catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void mockOn(View view) {
        startService(new Intent(this, LocationProviderService.class));
    }

    public void mockOff(View view) {
        stopService(new Intent(this, LocationProviderService.class));
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        double altitude = location.getAltitude();
        float accuracy = location.getAccuracy(); // metros
        float speed = location.getSpeed(); // m/sg
        long time = location.getTime();

        String data = String.format(
                "%nLon: %s, Lat: %s, Alt: %s%nAcc: %s, Spe: %s, Date: %s%n",
                longitude, latitude, altitude, accuracy, speed, new Date(time));
        positions.setText(positions.getText() + data);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
