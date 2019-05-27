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
    public void LoginAdministrador() throws Exception {

        String E = "UserNameXX123XXAdministradorXXXX0XXXXXX";
        String A= "";

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
                String A = response.body().toString();
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void LoginConductor() {

        String E = "UserNameXX123XXConductorXXXX0XXXXXX";
        String A= "";

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
                    String A = response.body().string();
                } catch (IOException e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void LoginPasajero() {

        String E = "UserNameXX123XXPasajeroXXXX0XXXXXX";
        String A= "";

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
                    String A = response.body().string();
                    assertEquals(E, A);
                } catch (IOException e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void ReservaRuta() {

        String E = "123,A,27/05/2019,Concepto,B";
        String A= "";

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

        Call call3 = service.AccesoGeneral("LeerReserva,"+"UserName,"+"123,"+1);
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call _, Response response) {
                try {
                    String A = response.body().toString();
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void HacerPagosTarjeta() {

        String E = "Pago creado, ID: 123";
        String A= "";

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

        Call call3 = service.AccesoGeneral("crearCredito,"+"UserName"+","+"123,"+0+"-"+"UserName"+"-"+"Conductor"+"-"+(float)3000+"-"
                +"NumTarjeta,Tipo,CVV"+"-");
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>  _, Response<ResponseBody>  response) {
                try {
                    String A = response.body().string();
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void HacerPagosEfectivo() {

        String E = "Pago creado, ID: 123";
        String A= "";

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

        Call call3 = service.AccesoGeneral("crearEfectivo,"+"UserName"+","+"123,"+0+"-"+"UserName"+"-"+"Conductor"+"-"+(float)3000+"-"
                +"Moneda,Monto dado,Monto devuelto"+"-");
        call3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>  _, Response<ResponseBody>  response) {
                try {
                    String A = response.body().string();
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void VerPagosPasajero() {

        String E = "";
        String A= "";

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

        Call call3 = service.AccesoGeneral("crearCredito,"+"UserName"+","+"123,"+0+"-"+"UserName"+"-"+"Conductor"+"-"+(float)3000+"-"
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

        Call call5 = service.AccesoGeneral("crearEfectivo,"+"UserName"+","+"123,"+0+"-"+"UserName"+"-"+"Conductor"+"-"+(float)3000+"-"
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
                    String A = response.body().toString();
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void CrearGrupo() {

        String E = "Agrupacion creada";
        String A= "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call call = service.AccesoGeneral("crearAgrupacion,"+"UserName,"+"123,"+"NG"+"-");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call _,Response response) {
                try {
                    String A = response.body().toString();
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }

    @Test
    public void CrearTrnasporteIndividual() {

        String E = "Vehículo Creado";
        String A= "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call call = service.AccesoGeneral("crearVehículo,"+"UserName,"+"123,"+"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call _,Response response) {
                try {
                    String A = response.body().toString();
                } catch (Exception e) {e.printStackTrace();}
            }
            @Override
            public void onFailure(Call<ResponseBody> _, Throwable t) {t.printStackTrace();}
        });
        assertEquals(E, A);
    }
}
