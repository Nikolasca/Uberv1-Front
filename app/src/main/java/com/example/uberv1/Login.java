package com.example.uberv1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {
    TextView Mensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
               .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        final Button buttonL = findViewById(R.id.buttonL);
        final EditText Nombre = findViewById(R.id.user);
        final EditText Pass = findViewById(R.id.pass);
        final TextView textView;
        Mensaje = (TextView) findViewById(R.id.textViewLogin);
        // textView = findViewById(R.id.textView);

        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.AccesoGeneral("Consultar_Usuario,"+Nombre.getText().toString()+","+Pass.getText().toString()+","+Nombre.getText().toString()+"-"+Pass.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {
                            System.out.println("Respuesta: "+response.body().toString());
                            String CC = response.body().string();

                            String[] parts = CC.split("XX");
                            System.out.println("Largo:"+ CC.length());
                            Mensaje.setText(parts[0]);
                            // textView.setText(parts[0]);
                            if (("UsuarioAceptado").compareToIgnoreCase(parts[0]) == 0) {


                                Intent Log = new Intent(Login.this, PerfilGenerico.class);
                                Log.putExtra("nombre", parts[1]);
                                Log.putExtra("Tipo", parts[3]);
                                Log.putExtra("Email", parts[5]);
                                Log.putExtra("Pass", parts[4]);
                                 Log.putExtra("id", parts[6]);
                                System.out.println("Lat :"+parts[7]);
                                System.out.println("Long :"+parts[8]);
                                startActivity(Log);
                            }
                        } catch (IOException e) {
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
            }
        });

    }

}
