package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Registro_2 extends AppCompatActivity {

    Button Registro;
    EditText Nombre,Password,Telefono,Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_2);
        Registro=(Button) findViewById(R.id.Registro);
        Nombre =(EditText) findViewById(R.id.Nombre);
        Password =(EditText) findViewById(R.id.Password);
        Telefono=(EditText) findViewById(R.id.Telefono);
        Email=(EditText) findViewById(R.id.Email);

    }
}
