package unicauca.movil.parkinapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import unicauca.movil.parkinapp.models.Parqueadero;

/**
 * Created by Natic_000 on 15/12/2016.
 */

public class ParqueaderoAdapter extends BaseAdapter {

    Context context;
    List<Parqueadero> data;

    public ParqueaderoAdapter(Context context, List<Parqueadero> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        //if(v == null)
            //v.inflate(context, R.la)




        return v;
    }
}
