package com.example.uberv1;

import android.Manifest;
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
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PerfilGenerico extends AppCompatActivity {
    Button IrGenerarViaje, prueba;
    ImageButton  IrHistorialViajes, IrHistorialPagos, IrPerfil;
    EditText Nobe, Type, Email, Telefono;
    TextView texto;
    private FusedLocationProviderClient mFusedLocation;
    LatLng LocationLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);




        String nombre = getIntent().getExtras().getString("nombre");
        String tipo = getIntent().getExtras().getString("Tipo");
        String em = getIntent().getExtras().getString("Email");
        String id = getIntent().getExtras().getString("id");

        //String tel = getIntent().getExtras().getString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_generico);
        Nobe = (EditText) findViewById((R.id.prueba));
        Type = (EditText) findViewById((R.id.tipoUser));
        Email = (EditText) findViewById(R.id.Email);
        Telefono = (EditText) findViewById(R.id.telefono);
        System.out.println(nombre);
        prueba = (Button) findViewById(R.id.botonp);
        texto = (TextView) findViewById(R.id.texto);
        Nobe.setText(nombre);
        Type.setText(tipo);
        Email.setText(em);
        texto.setText(id);
        Telefono.setText(id);
        IrGenerarViaje = (Button) findViewById(R.id.IrGenerarViaje);
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mFusedLocation.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
            }
            Location L = task.getResult();
            LocationLog = new LatLng(L.getLatitude(),L.getLongitude());

            Call<ResponseBody> call = service.ActualizarLocation(Integer.parseInt(id),L.getLatitude(),L.getLongitude());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> _,
                                       Response<ResponseBody> response) {
                    try {
                         System.out.print(response.body().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        // textView.setText(e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> _, Throwable t) {
                    t.printStackTrace();
                    //   textView.setText(t.getMessage());
                }
            });

        });


    }

}