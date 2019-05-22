package com.example.uberv1;

import android.Manifest;
import android.arch.lifecycle.Transformations;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.maps.GeoApiContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import  static  Util.Constantes.MAPVIEW_BUNDLE_KEY;

public class Maps extends Fragment implements OnMapReadyCallback {
    private FusedLocationProviderClient mFusedLocation;

    private MapView mMapView;
    private RecyclerView mUserListRecyclerView;
    private GeoApiContext mGeoApicontext = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mFusedLocation = LocationServices.getFusedLocationProviderClient(getActivity());
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView (@NonNull
    LayoutInflater inflater, @Nullable
    ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_maps, container, false);
       mUserListRecyclerView = view.findViewById(R.id.user_list_recycler_view);
        mMapView = view.findViewById(R.id.user_list_map);

        initUserListRecyclerView();
        initGoogleMap(savedInstanceState);

        return view;
    }
    private void initGoogleMap(Bundle savedInstanceState){
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }
    private void initUserListRecyclerView() {

        mUserListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);
        map.getMaxZoomLevel();
        map.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity()
                , android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mFusedLocation.getLastLocation().addOnCompleteListener(task -> {
            if(task.isSuccessful()){}
            Location L =task.getResult();
            Log.d("Hola","Latitud" + L.getLatitude());
            Log.d("Hola","Longitud" + L.getLongitude());
            LatLng currentL = new LatLng(L.getLatitude(),L.getLongitude());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentL,15));


        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final HerokuService service = retrofit.create(HerokuService.class);

        Call <List<Usuario>> call = service.TraerConductores();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response
                    <List<Usuario>> response) {
                try {

                    List<Usuario> usuarios = response.body();
                    String[]  Conductores = new String[usuarios.size()];
                    for (int i = 0; i < usuarios.size(); i++) {
                        LatLng Conductor = new LatLng(Double.parseDouble(usuarios.get(i).getLat()), Double.parseDouble(usuarios.get(i).getLongs()));
                        map.addMarker(new MarkerOptions().position(Conductor).title("Conductor: "+usuarios.get(i).getUsuario()));

                    }

                    //  JSONArray lista = obj.optJSONArray("");
                    //   JSONObject json_data = lista.getJSONObject(0);

                    //   System.out.println(json_data.get("nombre").toString()+"JSON");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> _, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });



    }




    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    private void getLastL() {



    }
}
