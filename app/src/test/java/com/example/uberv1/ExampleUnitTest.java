package com.example.uberv1;

import android.content.Intent;
import android.provider.ContactsContract;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void Login() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Pasajero","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _,
                                   Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("Consultar_Usuario,"+"UserName"+","+"123"+","+"UserName"+"-"+"123");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _,
                                   Response<ResponseBody> response) {
                try {
                    System.out.println("Respuesta: "+response.body().toString());
                    String CC = response.body().string();
                    assertEquals("UserNameXX123XXPasajeroXXName LastXX6746647XX1234567890XXa@g.comXX", CC);
                } catch (IOException e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
    }
}