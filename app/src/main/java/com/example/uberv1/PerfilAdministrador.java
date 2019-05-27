package com.example.uberv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PerfilAdministrador extends AppCompatActivity {
    EditText Nombre,Telefono,Email,Tipo ;
    Button IrRegistroUsuario,IrConsultarUsuario,IrEditarUsuario, IrGrupoBase, IrVehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_administrador);
        String nombre = getIntent().getExtras().getString("nombre");
        String tipo = getIntent().getExtras().getString("Tipo");

        Nombre=(EditText) findViewById(R.id.Nombre);
        Telefono=(EditText) findViewById(R.id.Telefono);
        Email=(EditText) findViewById(R.id.Email);
        Tipo=(EditText) findViewById(R.id.Tipo);
        IrGrupoBase = (Button) findViewById(R.id.agregar);
        Nombre.setText(nombre);
        Tipo.setText(tipo);
        IrVehiculo = (Button) findViewById(R.id.AgregarV);
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
        IrGrupoBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrGrupo = new Intent(PerfilAdministrador.this,AgregarVehiculo.class);
                startActivity(IrGrupo);
            }
        });
            IrVehiculo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent IrGrupo = new Intent(PerfilAdministrador.this,AnadirVehiculo.class);
                    startActivity(IrGrupo);
                }
            });
    }
}
