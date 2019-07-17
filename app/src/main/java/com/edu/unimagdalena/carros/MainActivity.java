package com.edu.unimagdalena.carros;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView lstOpciones;
    Intent i;
    private ArrayList<Carro> carros;
    private LinearLayoutManager llm;
    private String db = "Carros";
    private DatabaseReference databaseReference;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lstOpciones = findViewById(R.id.lstCarros);
        carros = new ArrayList<>();
        final AdaptadorCarro adapter = new AdaptadorCarro(carros);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        lstOpciones.setLayoutManager(llm);
        lstOpciones.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carros.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Carro c = snapshot.getValue(Carro.class);
                        carros.add(c);
                    }
                }

                adapter.notifyDataSetChanged();
                Datos.setCarros(carros);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void agregarCarro(View v){
        i = new Intent(MainActivity.this, AgregarCarro.class);
        startActivity(i);;
        finish();
    }

}
