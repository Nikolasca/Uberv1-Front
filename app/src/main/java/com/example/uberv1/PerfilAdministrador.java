package com.example.uberv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PerfilAdministrador extends AppCompatActivity {
    EditText Nombre,Telefono,Email,Tipo ;
    Button IrRegistroUsuario,IrConsultarUsuario,IrEditarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_administrador);
        Nombre=(EditText) findViewById(R.id.Nombre);
        Telefono=(EditText) findViewById(R.id.Telefono);
        Email=(EditText) findViewById(R.id.Email);
        Tipo=(EditText) findViewById(R.id.Tipo);
        IrRegistroUsuario=(Button) findViewById(R.id.IrRegistroUsuario);
        IrRegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrRegistroUsuario= new Intent(PerfilAdministrador.this,RegistroPasajero.class);
                startActivity(IrRegistroUsuario);
            }
        });
        IrConsultarUsuario=(Button) findViewById(R.id.IrConsultarUsuario);
        IrConsultarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrConsultarUsuario= new Intent(PerfilAdministrador.this,ConsultarUsuario.class);
                startActivity(IrConsultarUsuario);
            }
        });
        IrEditarUsuario=(Button) findViewById(R.id.IrEditarUsuario);
        IrEditarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrEditarUsuario= new Intent(PerfilAdministrador.this,EditarUsuario.class);
                startActivity(IrEditarUsuario);
            }
        });

    }
}
