package practicas.cursoandroid.practicasandroid.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import practicas.cursoandroid.practicasandroid.R;

public class SynchronizationService extends IntentService {

    public SynchronizationService() {
        super("SynchronizationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Metodo ejecutado de forma asincrona con hilo propio
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Ir a página web")
                .setContentText("Ver histórico sincronizaciones");

        PendingIntent activity =
                PendingIntent.getActivity(
                        getApplicationContext(),
                        100,
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.apache.org")),
                        Intent.FLAG_ACTIVITY_NEW_TASK);

        notificationBuilder.setContentIntent(activity);
        notificationBuilder.setAutoCancel(true);
        manager.notify(1000, notificationBuilder.build());
    }
}
