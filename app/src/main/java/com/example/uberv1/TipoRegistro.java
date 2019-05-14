package com.example.uberv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipoRegistro extends AppCompatActivity {

Button IrRegistroPasajero,IrRegistroConductor,IrRegistroAdministrador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_registro);

        IrRegistroPasajero= (Button)findViewById(R.id.IrRegistroPasajero);
        IrRegistroPasajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrRegistroPasajero = new Intent(TipoRegistro.this,RegistroPasajero.class);
                startActivity(IrRegistroPasajero);
            }
        });

        IrRegistroConductor= (Button)findViewById(R.id.IrRegistroConductor);
        IrRegistroConductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrRegistroConductor = new Intent(TipoRegistro.this,RegistroConductor.class);
                startActivity(IrRegistroConductor);
            }
        });

        IrRegistroAdministrador= (Button)findViewById(R.id.IrRegistroAdministrador);
        IrRegistroAdministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrRegistroAdministrador = new Intent(TipoRegistro.this,RegistroAdministrador.class);
                startActivity(IrRegistroAdministrador);
            }
        });

    }
}
