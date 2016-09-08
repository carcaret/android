package practicas.cursoandroid.practicasandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import practicas.cursoandroid.practicasandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent launch;
        switch (item.getItemId()) {
            case R.id.mnu_main_activities_lifecycle:
                launch = new Intent(this, LifecycleActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_fragments:
                launch = new Intent(this, FragmentsActivity.class);
                launch.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_dialogs:
                launch = new Intent(this, DialogsActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_location:
                launch = new Intent(this, LocationActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_google_maps:
                launch = new Intent(this, GoogleMapsActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_broadcast_receiver:
                launch = new Intent(this, BroadcastReceiverActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_alarm:
                launch = new Intent(this, AlarmActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_web_content:
                launch = new Intent(this, WebContentActivity.class);
                startActivity(launch);
                break;
            case R.id.mnu_main_activities_services:
                launch = new Intent(this, TestServicesActivity.class);
                startActivity(launch);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
