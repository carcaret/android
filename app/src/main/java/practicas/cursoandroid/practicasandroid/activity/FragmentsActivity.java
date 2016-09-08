package practicas.cursoandroid.practicasandroid.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import practicas.cursoandroid.practicasandroid.R;
import practicas.cursoandroid.practicasandroid.fragment.MyListFragment;
import practicas.cursoandroid.practicasandroid.fragment.MyTableFragment;

public class FragmentsActivity extends AppCompatActivity {

    private View leftFrame;
    private View rightFrame;

    private MyListFragment list;
    private MyTableFragment table;

    private boolean navigable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        leftFrame = findViewById(R.id.leftFrame);
        rightFrame = findViewById(R.id.rightFrame);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragments, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.mnu_fragments_showList).setEnabled(list == null);
        menu.findItem(R.id.mnu_fragments_hideList).setEnabled(list != null);
        menu.findItem(R.id.mnu_fragments_showTable).setEnabled(table == null);
        menu.findItem(R.id.mnu_fragments_hideTable).setEnabled(table != null);
        menu.findItem(R.id.mnu_fragments_swap).setEnabled(list != null && table != null);
        menu.findItem(R.id.mnu_fragments_navigable).setChecked(navigable);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.mnu_fragments_showList:
                list = new MyListFragment();
                transaction.add(R.id.leftFrame, list, "list");
                break;
            case R.id.mnu_fragments_showTable:
                table = new MyTableFragment();
                transaction.add(R.id.rightFrame, table, "table");
                break;
            case R.id.mnu_fragments_hideList:
                transaction.remove(list);
                list = null;
                break;
            case R.id.mnu_fragments_hideTable:
                transaction.remove(table);
                table = null;
                break;
            case R.id.mnu_fragments_swap:
                table = new MyTableFragment();
                transaction.replace(R.id.leftFrame, table);
                list = new MyListFragment();
                transaction.replace(R.id.rightFrame, list);
                break;
            case R.id.mnu_fragments_navigable:
                navigable = !navigable;
                break;
        }
        if(navigable) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        return super.onOptionsItemSelected(item);
    }
}
