package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConsultarUsuario extends AppCompatActivity {
    EditText Usuario;
    Button Consultar;
    TextView Respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        Usuario = (EditText) findViewById(R.id.Usuario);
        Consultar=(Button)findViewById(R.id.Consultar);
        Respuesta=(TextView)findViewById(R.id.Respuesta);
    }
}
