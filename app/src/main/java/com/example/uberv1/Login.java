package com.example.uberv1;

import android.content.Intent;
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

public class Login extends AppCompatActivity {

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
       // textView = findViewById(R.id.textView);

        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = service.Login(Nombre.getText().toString(),Pass.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> _,
                                           Response<ResponseBody> response) {
                        try {

                            String[] parts =  response.body().string().split(",");
                           // textView.setText(parts[0]);
                            if(("Usuario Aceptado").compareToIgnoreCase(parts[0])==0){

                                Intent Log= new Intent(Login.this,PerfilGenerico.class);
                                Log.putExtra("nombre", parts[1]);
                                Log.putExtra("Tipo",parts[3]);
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
