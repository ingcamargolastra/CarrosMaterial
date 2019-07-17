package com.edu.unimagdalena.carros;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorCarro extends RecyclerView.Adapter<AdaptadorCarro.CarroViewHolder>{
    private ArrayList<Carro> carros;

    public AdaptadorCarro(ArrayList<Carro> carros){
        this.carros = carros;
    }

    @Override
    public CarroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //COMO VOY A MOSTRAR
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_carro, viewGroup,false);
        return new CarroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarroViewHolder carroViewHolder, int i) {
        //QUE VOY A MOSTRAR
        Carro c = carros.get(i);
        carroViewHolder.foto.setImageResource(c.getFoto());
        carroViewHolder.placa.setText(c.getPlaca());
        carroViewHolder.marca.setText(c.getMarca());
        carroViewHolder.modelo.setText(c.getModelo());
        carroViewHolder.traccion.setText(c.getTraccion());
    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarroViewHolder extends RecyclerView.ViewHolder{

        private ImageView foto;
        private TextView placa;
        private TextView marca;
        private TextView modelo;
        private TextView traccion;
        private View v;

        public CarroViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.foto);
            placa = v.findViewById(R.id.lbl_placa);
            marca = v.findViewById(R.id.lbl_marca);
            modelo = v.findViewById(R.id.lbl_modelo);
            traccion = v.findViewById(R.id.lbl_traccion);
        }
    }
}