package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditarUsuario extends AppCompatActivity {
EditText NombreActual,NuevoNombre;
Button Editar;
TextView Respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        NombreActual = (EditText) findViewById(R.id.NombreActual);
        NuevoNombre= (EditText) findViewById(R.id.NuevoNombre);
        Editar= (Button) findViewById(R.id.Editar);
        Respuesta = (TextView) findViewById(R.id.Respuesta);

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();
                final HerokuService service = retrofit.create(HerokuService.class);
                Call<ResponseBody> call = service.AccesoGeneral("mod_Usuario,"+"nikolas,"+"pass,"+NombreActual.getText().toString()+"-"+"usuario"+"-"+NuevoNombre.getText().toString()+"-");
                System.out.println("mod_Usuario,"+"nikolas,"+"pass,"+NombreActual.getText().toString()+"-"+"usuario"+"-"+NuevoNombre.getText().toString()+"-");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {

                            Respuesta.setText(response.body().string());



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
