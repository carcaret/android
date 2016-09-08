package practicas.cursoandroid.practicasandroid.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.content.TasksProvider;

public class TasksProviderActivity extends AppCompatActivity {

    public static final String TASKS_URI = "content://" + TasksProvider.PROVIDER_URI + "/tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_provider);
        Cursor cursor = getContentResolver().query(Uri.parse(TASKS_URI), null, null, null, null);
        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()) {
            sb.append(readLine(cursor));
            while(cursor.moveToNext()) {
                sb.append(readLine(cursor));
            }
        }
        TextView tasks = (TextView) findViewById(R.id.txt_tasks_provider_tasks);
        tasks.setText(sb.toString());
        cursor.close();
    }

    private String readLine(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        String desc = cursor.getString(cursor.getColumnIndex("DESC"));
        Date date = new Date(cursor.getLong(cursor.getColumnIndex("DATE")));
        return String.format("ID: %s%nNombre: %s%nDescripcion: %s%nFecha: %s%n%n",
                id, name, desc, date);
    }
}
