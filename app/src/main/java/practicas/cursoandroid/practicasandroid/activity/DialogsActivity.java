package practicas.cursoandroid.practicasandroid.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import practicas.cursoandroid.practicasandroid.R;

public class DialogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_speaker_off);
        toast.setView(image);
        toast.show();
    }

    public void showDialog(View view) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Servicio de datos");
        alert.setMessage("Problema al acceder al servicio de datos.\nIndique como continuar.");
        alert.setButton(AlertDialog.BUTTON_POSITIVE,
                "Reintentar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                DialogsActivity.this,
                                "Quiere reintentar",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        alert.setButton(AlertDialog.BUTTON_NEGATIVE,
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                DialogsActivity.this,
                                "Quiere cancelar",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        alert.setButton(AlertDialog.BUTTON_NEUTRAL,
                "Notificar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                DialogsActivity.this,
                                "Quiere notificar",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
        alert.show();
    }

    public void showProgress(View view) {
        final ProgressDialog progress = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        progress.setTitle("Servicio consultas");
        progress.setMessage("Accediendo a datos ...");
        progress.show();
        final Handler hide = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    hide.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.hide();
                        }
                    });
                }
            }
        }).start();
    }

    public void showDate(View view) {
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(DialogsActivity.this,
                        String.format("%s/%s/%s", dayOfMonth, monthOfYear, year),
                        Toast.LENGTH_LONG).show();
            }
        }, 2016, 9, 6);
        date.show();
    }

    public void showHour(View view) {
        TimePickerDialog time = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(DialogsActivity.this,
                        String.format("%s:%s", hourOfDay, minute),
                        Toast.LENGTH_LONG).show();
            }
        }, 11, 00, true);
        time.show();
    }
}
