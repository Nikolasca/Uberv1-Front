package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Prueba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        Maps m =  new Maps();

       String nombre = getIntent().getExtras().getString("nombre");
       int id = Integer.parseInt(getIntent().getExtras().getString("id"));
        getSupportFragmentManager().beginTransaction().replace(R.id.map_container, m).commit();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("nombre",nombre);
        m.setArguments(bundle);
    }
}
