package practicas.cursoandroid.practicasandroid.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.receiver.EventReceiver;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private EventReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
    }

    public void on(View view) {
        receiver = new EventReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(getString(R.string.SYCHRONIZATION_ACTION));
        registerReceiver(receiver, filter);
    }

    public void off(View view) {
        if(receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    public void send(View view) {
        Intent synchronization = new Intent(getString(R.string.SYCHRONIZATION_ACTION));
        synchronization.putExtra("length", 2048);
        sendBroadcast(synchronization);
    }
}
