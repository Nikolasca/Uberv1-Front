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

public class HistorialViajes extends AppCompatActivity {
TextView reserva;
Button btn;
EditText r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_viajes);
        reserva = (TextView) findViewById(R.id.textViewR);
        btn = (Button) findViewById(R.id.buttonreserva);
        r = (EditText) findViewById(R.id.TextRes);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.AccesoGeneral("LeerReserva,"+"nikolas,"+"pass,"+Integer.parseInt(r.getText().toString())+"-");
                System.out.println("LeerReserva,"+"nikolas,"+"pass,"+Integer.parseInt(r.getText().toString())+"-");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {
                            reserva.setText(response.body().string());
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
