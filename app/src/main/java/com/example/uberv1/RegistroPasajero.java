package com.example.uberv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class RegistroPasajero extends AppCompatActivity {
TextView Pass2;
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

        final CheckBox pasajero = findViewById(R.id.checkPasajero);
        final CheckBox conductor = findViewById(R.id.checkConductor);
        final CheckBox admin = findViewById(R.id.checkAdmin);
        final EditText telefono = findViewById(R.id.Telefono);
        final EditText documento = findViewById(R.id.Documento);
        final CheckBox checkAdmin = findViewById(R.id.checkAdmin);
        final CheckBox checkPasajero = findViewById(R.id.checkPasajero);
        final CheckBox checkConductor = findViewById(R.id.checkConductor);
        Pass2 = (TextView) findViewById(R.id.textView6);

        buttonR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tipo ="";
                    if(checkPasajero.isChecked()){
                        tipo = "Pasajero";
                    }
                    if (checkConductor.isChecked()){

                        tipo= "Conductor";
                    }
                    if (checkAdmin.isChecked()){

                        tipo= "Administrador";
                    }
                    System.out.println(tipo);
                Call<ResponseBody> call = service.Register(Nombre.getText().toString(),Email.getText().toString(),Integer.parseInt(telefono.getText().toString()),Pass.getText().toString(),tipo);
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
