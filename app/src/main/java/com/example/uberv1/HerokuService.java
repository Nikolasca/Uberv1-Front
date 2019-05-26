package com.example.uberv1;
        import android.service.media.MediaBrowserService;

        import java.util.List;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.Field;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.GET;
        import retrofit2.http.HTTP;
        import retrofit2.http.POST;
        import retrofit2.http.Path;

public interface HerokuService {
    @GET("prueba")
    Call<ResponseBody> prueba2();

    @FormUrlEncoded
    @POST("servicio/Hola")
    Call<ResponseBody> Test(@Field("name") String s);

    @FormUrlEncoded
    @POST("servicio/Register")
    Call<ResponseBody> Register(@Field("name") String name,
                                @Field("email") String email,
                                @Field("telefono") int telefono,
                                @Field("pass") String pass,
                                @Field("type") String type);

    @FormUrlEncoded
    @POST("servicio/Login")
    Call<ResponseBody> Login(@Field("user") String name,
                             @Field("pass") String pass);

    @GET("servicio/Conductores")
    Call<List<Usuario>> TraerConductores();

    @FormUrlEncoded
    @POST("servicio/UpdateL")
    Call<ResponseBody> ActualizarLocation(@Field("id") int id,@Field("Lat") double Lat,@Field("Long") double Long);


    @FormUrlEncoded
    @POST("servicio/Acceso")
    Call<ResponseBody> AccesoGeneral(@Field("cadena") String Cadena);

    @FormUrlEncoded
    @POST("servicio/Crear")
    Call<ResponseBody>Crear(@Field("cadena") String Cadena);






}
