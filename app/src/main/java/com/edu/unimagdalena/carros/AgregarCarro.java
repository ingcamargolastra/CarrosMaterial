package com.edu.unimagdalena.carros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCarro extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText placa;
    private Spinner cmbMarca, cmbModelo, cmbTraccion;
    private String marcas[], modelos[], tracciones[];
    private boolean placaExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);
        placaExist = false;

        //inicializamos el array de fotos
        fotos = new ArrayList<>();
        fotos.add(R.drawable.imagen1);
        fotos.add(R.drawable.imagen2);
        fotos.add(R.drawable.imagen3);
        fotos.add(R.drawable.imagen4);

        //obtenemos los objetos utilizados
        placa = findViewById(R.id.txtPlaca);
        cmbMarca = findViewById(R.id.cmbMarca);
        cmbModelo = findViewById(R.id.cmbModelo);
        cmbTraccion = findViewById(R.id.cmbTraccion);

        //Traemos los materiales, dijes, tipos y monedas en Arrays
        marcas = getResources().getStringArray(R.array.marcas);
        modelos = getResources().getStringArray(R.array.modelos);
        tracciones = getResources().getStringArray(R.array.tracciones);

        //Creamos los adapter indicando donde se va a colocar como se va a visualizar y que se va a mostrar
        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, marcas);
        ArrayAdapter<String> adapterModelos = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, modelos);
        ArrayAdapter<String> adapterTracciones = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, tracciones);

        //pasamos adaptar a cada combo
        cmbMarca.setAdapter(adapterMarcas);
        cmbModelo.setAdapter(adapterModelos);
        cmbTraccion.setAdapter(adapterTracciones);
    }

    public void guardar(View v){
        if(validar(v)){
            Carro c = new Carro(Datos.getId(), this.fotoXmarca(cmbMarca.getSelectedItemPosition()),placa.getText().toString(),cmbMarca.getSelectedItem().toString(),cmbModelo.getSelectedItem().toString(), cmbTraccion.getSelectedItem().toString());
            c.guardar();
            limpiar();
            Snackbar.make(v,R.string.mensaje, Snackbar.LENGTH_SHORT).show();
        }
    }

    public boolean validar(View v){
        if (placa.getText().toString().isEmpty()){
            placa.setError(getResources().getString(R.string.error_required));
            placa.requestFocus();
            return false;
        }else if (placa.getText().toString().length() != 6){
            placa.setError(getResources().getString(R.string.error_tamanio_placa));
            placa.selectAll();
            return false;
        }else if (Datos.consultarPlaca(placa.getText().toString())){
            placa.setError(getResources().getString(R.string.error_existe));
            placa.requestFocus();
            return false;
        }else if(cmbMarca.getSelectedItemPosition() == 0){
            ((TextView)cmbMarca.getSelectedView()).setError(getResources().getString(R.string.marca_required));
            Snackbar.make(v,R.string.marca_required, Snackbar.LENGTH_SHORT).show();
            return false;
        }else if(cmbModelo.getSelectedItemPosition() == 0){
            ((TextView)cmbModelo.getSelectedView()).setError(getResources().getString(R.string.modelo_required));
            Snackbar.make(v,R.string.modelo_required, Snackbar.LENGTH_SHORT).show();
            return false;
        }else if(cmbTraccion.getSelectedItemPosition() == 0){
            ((TextView)cmbTraccion.getSelectedView()).setError(getResources().getString(R.string.traccion_required));
            Snackbar.make(v,R.string.traccion_required, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        placa.setText("");
        cmbMarca.setSelection(0);
        cmbModelo.setSelection(0);
        cmbTraccion.setSelection(0);
        placa.setError(null);
        ((TextView)cmbMarca.getSelectedView()).setError(null);
        ((TextView)cmbModelo.getSelectedView()).setError(null);
        ((TextView)cmbTraccion.getSelectedView()).setError(null);
        placaExist = false;
        placa.requestFocus();
    }

    public int fotoXmarca(int posicion){
        return fotos.get(posicion-1);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCarro.this, MainActivity.class);
        startActivity(i);
    }
}
