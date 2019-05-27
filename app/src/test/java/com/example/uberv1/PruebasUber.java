package com.example.uberv1;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static org.junit.Assert.assertEquals;

public class PruebasUber {

    @Test
    public void LoginAdministrador() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Administrador", "", 0, "", "");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("Consultar_Usuario,UserName,123,UserName-123");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
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

    @Test
    public void LoginConductor() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Conductor","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("Consultar_Usuario,UserName,123,UserName-123");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {
                    String CC = response.body().string();
                    assertEquals("UserNameXX123XXConductorXXName LastXX6746647XX1234567890XXa@g.comXX", CC);
                } catch (IOException e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
    }

    @Test
    public void LoginPasajero() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Pasajero","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("Consultar_Usuario,UserName,123,UserName-123");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
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

    @Test
    public void Reservas() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Pasajero","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("CrearReserva,"+"UserName"+","+"123,"+"A"+"-"+"27/05/2019-"+"Concepto"+"-"+"B"+"-");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try { //String C = response.body().string(); assertEquals("Se creó reserva, ID: 123", C);
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call call3 = service.AccesoGeneral("LeerReserva,"+"UserName,"+"123,"+1);
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call _, Response response) {
                try {
                    String CC = response.body().toString();
                    assertEquals("123,A,27/05/2019,Concepto,B", CC);
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
    }

    @Test
    public void HacerPagosTarjetas() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Pasajero","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("CrearReserva,"+"UserName"+","+"123,"+"A"+"-"+"27/05/2019-"+"Concepto"+"-"+"B"+"-");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call call3 = service.AccesoGeneral("crearCredito,"+"UserName"+","+"123,"+0+"-"+"Pasajero"+"-"+"Conductor"+"-"+(float)3000+"-"
                +"NumTarjeta,Tipo,CVV"+"-");
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>  _, Response<ResponseBody>  response) {
                try {
                    String CC = response.body().string();
                    assertEquals("Pago creado, ID: 123", CC);
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
    }

    @Test
    public void HacerPagosEfectivo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Pasajero","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("CrearReserva,"+"UserName"+","+"123,"+"A"+"-"+"27/05/2019-"+"Concepto"+"-"+"B"+"-");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {
                t.printStackTrace();
            }
        });

        Call call3 = service.AccesoGeneral("crearEfectivo,"+"UserName"+","+"123,"+0+"-"+"Pasajero"+"-"+"Conductor"+"-"+(float)3000+"-"
                +"Moneda,Monto dado,Monto devuelto"+"-");
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>  _, Response<ResponseBody>  response) {
                try {
                    String CC = response.body().string();
                    assertEquals("Pago creado, ID: 123", CC);
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
    }

    @Test
    public void VerPagos() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call<ResponseBody> call = service.Crear("UserName","123","Pasajero","Name Last",6746647,
                "1234567890", "a@g.com");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call2 = service.AccesoGeneral("CrearReserva,"+"UserName"+","+"123,"+"A"+"-"+"27/05/2019-"+"Concepto"+"-"+"B"+"-");
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call call3 = service.AccesoGeneral("crearCredito,"+"UserName"+","+"123,"+0+"-"+"Pasajero"+"-"+"Conductor"+"-"+(float)3000+"-"
                +"NumTarjeta,Tipo,CVV"+"-");
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>  _, Response<ResponseBody>  response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call<ResponseBody> call4 = service.AccesoGeneral("CrearReserva,"+"UserName"+","+"123,"+"C"+"-"+"27/05/2019-"+"Concepto"+"-"+"D"+"-");
        call4.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> _, Response<ResponseBody> response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {
                t.printStackTrace();
            }
        });

        Call call5 = service.AccesoGeneral("crearEfectivo,"+"UserName"+","+"123,"+0+"-"+"Pasajero"+"-"+"Conductor"+"-"+(float)3000+"-"
                +"Moneda,Monto dado,Monto devuelto"+"-");
        call5.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>  _, Response<ResponseBody>  response) {
                try {} catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });

        Call call6 = service.AccesoGeneral("verPagosP,"+"UserName,"+"123,"+"UserName"+"-");
        call6.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call _, Response response) {
                try {
                    System.out.println(response.body().toString());
                    String CC = response.body().toString();
                    //assertEquals("Pago creado, ID: 123", CC);
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
    }
}
