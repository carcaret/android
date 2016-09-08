package practicas.cursoandroid.practicasandroid.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import practicas.cursoandroid.practicasandroid.R;

public class ReadSensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sensor);
        data = (TextView) findViewById(R.id.txt_read_sensor_data);
        int sensorType = getIntent().getIntExtra("sensorType", -1);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        manager.registerListener(
                this, manager.getDefaultSensor(sensorType), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                float rotX = event.values[0]; // grados
                float rotY = event.values[1]; // grados
                float rotZ = event.values[2]; // grados
                data.setText(String.format("RotX: %s%nRotY: %s%nRotZ: %s",
                        rotX, rotY, rotZ));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                float acelX = event.values[0]; // m/sg2
                float acelY = event.values[1]; // m/sg2
                float acelZ = event.values[2]; // m/sg2
                data.setText(String.format("Acelx: %s%nAcelY: %s%nAcelZ: %s",
                        acelX, acelY, acelZ));
                break;
            case Sensor.TYPE_TEMPERATURE:
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                float temp = event.values[0]; // celsius
                data.setText(String.format("Temperatura: %s", temp));
                break;
            case Sensor.TYPE_STEP_COUNTER:
                float steps = event.values[0]; // pasos desde ultimo reinicio
                data.setText(String.format("Steps: %s", steps));
                break;
            case Sensor.TYPE_GYROSCOPE:
                float angularAcelX = event.values[0]; // rad/sg
                float angularAcelY = event.values[1]; // rad/sg
                float angularAcelZ = event.values[2]; // rad/sg
                data.setText(String.format("AngularAcelx: %s%nAngularAcelY: %s%nAngularAcelZ: %s",
                        angularAcelX, angularAcelY, angularAcelZ));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
