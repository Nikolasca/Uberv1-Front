package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AgregarVehiculo extends AppCompatActivity {
    EditText nombre;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculo);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        nombre = (EditText) findViewById(R.id.NombreAgru);
        btn = (Button) findViewById(R.id.buttonGrupo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.AccesoGeneral("crearAgrupacion,"+"nikolas,"+"pass,"+nombre.getText()+"-");
                System.out.println("crearAgrupacion,"+"nikolas,"+"pass,"+nombre.getText()+"-");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {

                            System.out.println(response.body().string());

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
