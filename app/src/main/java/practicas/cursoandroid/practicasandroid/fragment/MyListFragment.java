package practicas.cursoandroid.practicasandroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import practicas.cursoandroid.practicasandroid.R;

public class MyListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView list = (ListView) inflater.inflate(R.layout.fragment_list, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                new String[]{
                        "Linea datos uno",
                        "Linea datos dos",
                        "Linea datos tres"
                }
        );
        list.setAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
