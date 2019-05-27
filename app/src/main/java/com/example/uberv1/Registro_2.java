package com.example.uberv1;

import android.nfc.Tag;
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

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class Registro_2 extends AppCompatActivity {

    Button Registro;
    EditText Nombre,Password,Telefono,Email;
    TextView Mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_2);
        Registro=(Button) findViewById(R.id.Registro);
        Nombre =(EditText) findViewById(R.id.Nombre);
        Password =(EditText) findViewById(R.id.Password);
        Telefono=(EditText) findViewById(R.id.Telefono);
        Email=(EditText) findViewById(R.id.Email);
        Mensaje = (TextView) findViewById(R.id.textViewR);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                final HerokuService service = retrofit.create(HerokuService.class);

                Call<ResponseBody> call = service.Crear(Nombre.getText().toString(),Password.getText().toString(),"Pasajero","",Integer.parseInt(Telefono.getText().toString()),"", Email.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {
                            Mensaje.setText(response.body().string());
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
