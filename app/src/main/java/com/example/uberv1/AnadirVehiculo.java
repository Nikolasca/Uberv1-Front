package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AnadirVehiculo extends AppCompatActivity {
    EditText nombreG,NombreV,Tipo,Placa,Cantidad;
    Button btn;
    TextView tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_vehiculo);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        nombreG = (EditText) findViewById(R.id.nombreg);
        NombreV= (EditText) findViewById(R.id.nombrev);
        Tipo = (EditText) findViewById(R.id.tipov);
        Placa = (EditText) findViewById(R.id.placav);
        Cantidad = (EditText) findViewById(R.id.cantidadv);
        btn = (Button) findViewById(R.id.buttonV);
        tc = (TextView) findViewById(R.id.textView99);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.AccesoGeneral("crearVehiculo,nikolas,123,"+nombreG.getText().toString()+"-"+NombreV.getText().toString()+"-"+Tipo.getText().toString()+"-"+Placa.getText().toString()+"-"+Cantidad.getText().toString()+"-"+"A"+"-"+"B"+"-"+"C"+"-");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {
                            tc.setText(response.body().string());

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
