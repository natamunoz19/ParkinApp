package unicauca.movil.parkinapp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import unicauca.movil.parkinapp.R;
import unicauca.movil.parkinapp.databinding.TemplateParqueaderoBinding;
import unicauca.movil.parkinapp.models.Parqueadero;
import unicauca.movil.parkinapp.util.L;

/**
 * Created by Natic_000 on 15/12/2016.
 */

public class ParqueaderoAdapter extends RecyclerView.Adapter<ParqueaderoAdapter.ParqueaderoViewHolder> {

    public static class ParqueaderoViewHolder extends RecyclerView.ViewHolder{
        TemplateParqueaderoBinding binding;

        public ParqueaderoViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnParqueaderoListener{
        void onParqueadero(View v);
    }

    LayoutInflater inflater;
    OnParqueaderoListener onParqueaderoListener;

    public ParqueaderoAdapter(LayoutInflater inflater, OnParqueaderoListener onRestauranteListener) {
        this.onParqueaderoListener = onRestauranteListener;
        this.inflater = inflater;
    }

    @Override
    public ParqueaderoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = inflater.inflate(R.layout.template_parqueadero, parent, false);
        ParqueaderoViewHolder holder = new ParqueaderoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ParqueaderoViewHolder holder, int position) {
        holder.binding.setPar(L.data.get(position));
        holder.binding.setHandlerx(this);
    }

    @Override
    public int getItemCount() {
        return L.data.size();
    }

    public void onClickParqueadero(View v){
        onParqueaderoListener.onParqueadero(v);
    }

    //Retorna el tipo de view o Celda
    /*@Override
    public int getItemViewType(int position) { return 0;}*/


}
