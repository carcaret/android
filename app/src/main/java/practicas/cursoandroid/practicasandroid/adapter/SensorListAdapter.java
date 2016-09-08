package practicas.cursoandroid.practicasandroid.adapter;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import practicas.cursoandroid.practicasandroid.R;

public class SensorListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Sensor> sensors;

    public SensorListAdapter(Context context, List<Sensor> sensors) {
        this.context = context;
        this.sensors = sensors;
    }

    @Override
    public int getCount() {
        return sensors.size();
    }

    @Override
    public Object getItem(int position) {
        return sensors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.sensor_card, null);
        }
        Sensor sensor = sensors.get(position);

        TextView type = (TextView) view.findViewById(R.id.btn_sensor_card_type);
        type.setText("Tipo: " + sensor.getStringType());

        TextView vendor = (TextView) view.findViewById(R.id.btn_sensor_card_vendor);
        vendor.setText("Fabricante: " + sensor.getVendor());

        TextView power = (TextView) view.findViewById(R.id.btn_sensor_card_power);
        power.setText("Consumo: " + sensor.getPower());

        TextView resolution = (TextView) view.findViewById(R.id.btn_sensor_card_resolution);
        resolution.setText("Resolucion: " + sensor.getResolution());

        return view;
    }
}
