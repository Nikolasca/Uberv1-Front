package com.example.uberv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PerfilConductor extends AppCompatActivity {
  EditText Nombre,Telefono,Email,Tipo ;
  Button IrHistorialPagos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conductor);

        Nombre= (EditText) findViewById(R.id.Nombre);
        Telefono=(EditText) findViewById(R.id.Telefono);
        Email=(EditText) findViewById(R.id.Email);
        Tipo=(EditText) findViewById(R.id.Tipo);
        IrHistorialPagos=(Button) findViewById(R.id.IrHistorialPagos);
        IrHistorialPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  IrHistorialPagos = new Intent(PerfilConductor.this, com.example.uberv1.HistorialPagos.class);
                startActivity(IrHistorialPagos);
            }
        });

    }
}
