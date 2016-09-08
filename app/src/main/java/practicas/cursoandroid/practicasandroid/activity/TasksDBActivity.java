package practicas.cursoandroid.practicasandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.content.TaskDB;

public class TasksDBActivity extends AppCompatActivity {

    private EditText name;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_db);
        name = (EditText) findViewById(R.id.txt_tasks_db_name);
        desc = (EditText) findViewById(R.id.txt_tasks_db_desc);
    }

    public void save(View view) {
        try (TaskDB db = new TaskDB(this)){
            db.open();
            db.insert(name.getText().toString(), desc.getText().toString());
        }
    }
}
