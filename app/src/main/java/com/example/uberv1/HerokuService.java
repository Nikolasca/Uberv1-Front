package com.example.uberv1;
        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.Path;

public interface HerokuService {
    @GET("prueba")
    Call<ResponseBody> prueba2();

}
