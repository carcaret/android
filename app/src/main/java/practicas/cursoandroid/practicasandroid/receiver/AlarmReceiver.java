package practicas.cursoandroid.practicasandroid.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import practicas.cursoandroid.practicasandroid.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(context.getString(R.string.ALARM_ACTION))){
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setTitle("Alarma recibida");
            dialog.setMessage("Evento programado procesado");
            dialog.setButton(AlertDialog.BUTTON_POSITIVE,
                    "Aceptar", (DialogInterface.OnClickListener) null);
            dialog.show();
        }
    }
}
