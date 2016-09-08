package practicas.cursoandroid.practicasandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class EventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean state = intent.getBooleanExtra("state", false);
                Toast.makeText(context, "Modo avion cambiado: " + state, Toast.LENGTH_LONG).show();
                break;
            case "cursoandroid.SYNCHRONIZATION":
                int length = intent.getIntExtra("length", -1);
                Toast.makeText(context, "Sincronizacion: " + length, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
