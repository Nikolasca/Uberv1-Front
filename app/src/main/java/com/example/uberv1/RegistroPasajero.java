package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegistroPasajero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasajero);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        final Button buttonR = findViewById(R.id.buttonR);
        final EditText Nombre = findViewById(R.id.Nombre);
        final EditText Email = findViewById(R.id.Email);
        final EditText Pass = findViewById(R.id.Pass);
        final EditText Pass2 = findViewById(R.id.Pass2);

        buttonR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Call<ResponseBody> call = service.Register(Nombre.getText().toString(),Pass.getText().toString(),"Pasajero");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {
                            Pass2.setText(response.body().string());

                        } catch (IOException e) {
                            e.printStackTrace();
                            Pass2.setText(e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> _, Throwable t) {
                        t.printStackTrace();
                        Pass2.setText(t.getMessage());
                    }
            });
            }
        });

    }
}
