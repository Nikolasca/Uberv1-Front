package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HistorialPagos extends AppCompatActivity {
    Button btn;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pagos);
        String nombre = getIntent().getExtras().getString("name");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);
        texto = (TextView) findViewById(R.id.textViewPagos);
    btn = findViewById(R.id.traerpagos);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Call<ResponseBody> call = service.AccesoGeneral("verPagosP,"+"nikolas,"+"pass,"+nombre+"-");
            System.out.println("verPagosP,"+"nikolas,"+"pass,"+nombre+"-");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> _,
                                       Response<ResponseBody> response) {
                    try {
                        texto.setText(response.body().string());
                        System.out.println(response.body().toString());

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> _, Throwable t) {
                    t.printStackTrace();

                }
            });

        }
    });
    }
}
