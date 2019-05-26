package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditarUsuario extends AppCompatActivity {
EditText NombreActual,NuevoNombre;
Button Editar;
TextView Respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        NombreActual = (EditText) findViewById(R.id.NombreActual);
        NuevoNombre= (EditText) findViewById(R.id.NuevoNombre);
        Editar= (Button) findViewById(R.id.Editar);
        Respuesta = (TextView) findViewById(R.id.Respuesta);
    }
}
