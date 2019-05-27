package com.example.uberv1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PerfilConductor extends AppCompatActivity {


    private FusedLocationProviderClient mFusedLocation;
  EditText Nombre,Telefono,Email,Tipo ;
  Button IrHistorialPagos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conductor);
        String nombre = getIntent().getExtras().getString("nombre");
        String tipo = getIntent().getExtras().getString("Tipo");
        String em = getIntent().getExtras().getString("Email");
        String id = getIntent().getExtras().getString("id");
        String Pass = getIntent().getExtras().getString("Pass");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);


        Nombre= (EditText) findViewById(R.id.Nombre);
        Telefono=(EditText) findViewById(R.id.Telefono);
        Email=(EditText) findViewById(R.id.Email);
        Tipo=(EditText) findViewById(R.id.Tipo);
        Nombre.setText(nombre);
        Tipo.setText(tipo);
        Email.setText(em);

        IrHistorialPagos=(Button) findViewById(R.id.IrHistorialPagos);
        IrHistorialPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  IrHistorialPagos = new Intent(PerfilConductor.this, com.example.uberv1.HistorialPagos.class);
                startActivity(IrHistorialPagos);
            }
        });
        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mFusedLocation.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
            }
            Location L = task.getResult();

            Call<ResponseBody> call = service.ActP(Integer.parseInt(id),L.getLatitude(),L.getLongitude());


            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> _,
                                       Response<ResponseBody> response) {
                    try {
                        System.out.println("Respuesta:  "+response.body().string());
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
