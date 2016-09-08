package practicas.cursoandroid.practicasandroid.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.receiver.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity {

    private AlarmManager manager;
    private PendingIntent alarm;
    private AlarmReceiver receiver = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        IntentFilter filter = new IntentFilter(getString(R.string.ALARM_ACTION));
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void on(View view) {
        Intent alarmIntent = new Intent(getString(R.string.ALARM_ACTION));
        alarm = PendingIntent.getBroadcast(this, 100, alarmIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 20);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarm);
    }

    public void off(View view) {
        if(alarm != null) {
            manager.cancel(alarm);
        }
    }
}
