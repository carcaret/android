package practicas.cursoandroid.practicasandroid.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import practicas.cursoandroid.practicasandroid.adapter.SensorListAdapter;

public class SensorsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor heartSensor = manager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if(heartSensor != null) {
            Toast.makeText(this, "Hay pulsometro", Toast.LENGTH_LONG).show();
        }
        final List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        setListAdapter(new SensorListAdapter(this, sensors));
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReadSensorActivity.class);
                intent.putExtra("sensorType", sensors.get(position).getType());
                startActivity(intent);
                return true;
            }
        });
    }
}
