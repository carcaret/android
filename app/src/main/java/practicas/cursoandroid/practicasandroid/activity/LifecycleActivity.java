package practicas.cursoandroid.practicasandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import practicas.cursoandroid.practicasandroid.R;

public class LifecycleActivity extends AppCompatActivity {

    private String message = "Mensaje inicial";
    private TextView txt_message;
    private CheckBox chk_state;
    private Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        txt_message = (TextView) findViewById(R.id.txt_lifecycle_message);
        chk_state = (CheckBox) findViewById(R.id.chk_lifecycle_state);
        btn_change = (Button) findViewById(R.id.btn_lifecycle_change);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cambiar_onClick(v);
            }
        });

        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    public void btn_cambiar_onClick(View v) {
        message = "Nuevo mensaje";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(chk_state.isChecked()) {
            message = savedInstanceState.getString("message");
        }

        Toast.makeText(this, "onRestore()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        txt_message.setText(message);

        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(chk_state.isChecked()) {
            outState.putString("message", message);
        }

        Toast.makeText(this, "onSave()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
