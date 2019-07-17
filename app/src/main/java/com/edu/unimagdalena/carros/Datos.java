package com.edu.unimagdalena.carros;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Datos {
    private static String db = "Carros";

    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static ArrayList<Carro> carros = new ArrayList<>();

    public static void agregar(Carro c){
        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static void editar(Carro c){
        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static void eliminar(Carro c){
        databaseReference.child(db).child(c.getId()).removeValue();
    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void setCarros(ArrayList<Carro> carros){
        Datos.carros = carros;
    }

    public static ArrayList<Carro> obtener(){
        return Datos.carros;
    }

    public static boolean consultarPlaca(String placa){
        boolean placaEsta = false;
        for (Carro c:carros) {
            if (c.getPlaca().equalsIgnoreCase(placa)){
                placaEsta = true;
                break;
            }
        }
        return placaEsta;
    }


}
