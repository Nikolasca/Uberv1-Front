package com.example.uberv1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PerfilGenerico extends AppCompatActivity {

    ImageButton IrGenerarViaje,IrHistorialViajes,IrHistorialPagos,IrPerfil;
    EditText Nobe, Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String nombre = getIntent().getExtras().getString("nombre");
        String tipo = getIntent().getExtras().getString("Tipo");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_generico);
        Nobe = (EditText) findViewById((R.id.prueba));
        Type = (EditText) findViewById((R.id.tipoUser));
        System.out.println(nombre);

        Nobe.setText(nombre);
        Type.setText(tipo);
        IrGenerarViaje= (ImageButton)findViewById(R.id.IrGenerarViaje);
        IrGenerarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrGenerarViaje = new Intent(PerfilGenerico.this,Prueba.class);
                startActivity(IrGenerarViaje);
            }
        });
        IrHistorialViajes= (ImageButton)findViewById(R.id.IrHistorialViajes);
        IrHistorialViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrHistorialViajes = new Intent(PerfilGenerico.this,HistorialViajes.class);
                startActivity(IrHistorialViajes);
            }
        });
        IrHistorialPagos= (ImageButton)findViewById(R.id.IrHistorialPagos);
        IrHistorialPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrHistorialPagos = new Intent(PerfilGenerico.this,HistorialPagos.class);
                startActivity(IrHistorialPagos);
            }
        });
    }


}
