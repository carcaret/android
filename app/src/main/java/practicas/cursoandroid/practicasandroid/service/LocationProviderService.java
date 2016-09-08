package practicas.cursoandroid.practicasandroid.service;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;

import java.util.Random;

public class LocationProviderService extends Service implements Runnable {

    public static final String PROVIDER = "mock";

    private LocationManager manager;
    private boolean send = true;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(manager.getProvider(PROVIDER) != null) {
            manager.removeTestProvider(PROVIDER);
        }
        manager.addTestProvider(PROVIDER, true, true, true, false, true, true, true,
                Criteria.POWER_LOW, Criteria.ACCURACY_FINE);
        manager.setTestProviderEnabled(PROVIDER, true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(this).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.setTestProviderEnabled(PROVIDER, false);
        manager.removeTestProvider(PROVIDER);
        send = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void run() {
        Random random = new Random();
        while(send) {
            sleep(2000);
            manager.setTestProviderLocation(PROVIDER, buildLocation(random));
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Location buildLocation(Random random) {
        Location location = new Location(PROVIDER);
        location.setAltitude(1000.0 * random.nextDouble());
        location.setLatitude(90.0 * random.nextDouble());
        location.setLongitude(180.0 * random.nextDouble());
        location.setAccuracy(4.0f);
        location.setSpeed(10.0f);
        location.setTime(System.currentTimeMillis());
        location.setElapsedRealtimeNanos(System.nanoTime());
        return location;
    }
}
