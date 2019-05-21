package com.example.uberv1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class PerfilGenerico extends AppCompatActivity {

    ImageButton IrGenerarViaje, IrHistorialViajes, IrHistorialPagos, IrPerfil;
    EditText Nobe, Type, Email, Telefono;
    private FusedLocationProviderClient mFusedLocation;
    LatLng LocationLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String nombre = getIntent().getExtras().getString("nombre");
        String tipo = getIntent().getExtras().getString("Tipo");
        String em = getIntent().getExtras().getString("Email");
        //String tel = getIntent().getExtras().getString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_generico);
        Nobe = (EditText) findViewById((R.id.prueba));
        Type = (EditText) findViewById((R.id.tipoUser));
        Email = (EditText) findViewById(R.id.Email);
        Telefono = (EditText) findViewById(R.id.Telefono);
        System.out.println(nombre);

        Nobe.setText(nombre);
        Type.setText(tipo);
        Email.setText(em);
        IrGenerarViaje = (ImageButton) findViewById(R.id.IrGenerarViaje);
        IrGenerarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrGenerarViaje = new Intent(PerfilGenerico.this, Prueba.class);
                startActivity(IrGenerarViaje);
            }
        });
        IrHistorialViajes = (ImageButton) findViewById(R.id.IrHistorialViajes);
        IrHistorialViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrHistorialViajes = new Intent(PerfilGenerico.this, HistorialViajes.class);
                startActivity(IrHistorialViajes);
            }
        });
        IrHistorialPagos = (ImageButton) findViewById(R.id.IrHistorialPagos);
        IrHistorialPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrHistorialPagos = new Intent(PerfilGenerico.this, HistorialPagos.class);
                startActivity(IrHistorialPagos);
            }
        });
        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mFusedLocation.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
            }
            Location L = task.getResult();
            LocationLog = new LatLng(L.getLatitude(),L.getLongitude());
            System.out.println(LocationLog.toString());
        });



    }

}