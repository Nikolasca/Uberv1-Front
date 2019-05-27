package com.example.uberv1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.internal.DurationAdapter;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Duration;

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

import android.view.View.OnClickListener;
import android.widget.Button;

import  static  Util.Constantes.MAPVIEW_BUNDLE_KEY;

public class Maps extends Fragment implements  OnMapReadyCallback, OnClickListener,GoogleMap.OnInfoWindowClickListener, GoogleMap.OnPolylineClickListener {
    Button  Confirmar;
    private static final String TAG = "";
    private FusedLocationProviderClient mFusedLocation;
    private GoogleMap map;
    private String ConductorElegido;

    private String NombrePasajero;
    private int id;

    private MapView mMapView;
    private RecyclerView mUserListRecyclerView;
    private GeoApiContext mGeoApicontext = null;
    private Location loc;
    private ArrayList<PolylineData> mPolylinesData = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mFusedLocation = LocationServices.getFusedLocationProviderClient(getActivity());
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
                String nombre = getArguments().getString("nombre");
                int id = getArguments().getInt("id");

                this.NombrePasajero = nombre;
                this.id = id;



        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Confirmar = (Button) getView().findViewById(R.id.btn);
        Confirmar.setEnabled(false);

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
        if(mGeoApicontext == null) mGeoApicontext = new GeoApiContext.Builder()
                .apiKey("AIzaSyBzpncXoRqGc8Bp9UXgxWcatDv_-yIir9M")
                .build();
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
            this.loc = task.getResult();
    this.map =map;
            LatLng currentL = new LatLng(L.getLatitude(),L.getLongitude());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentL,15));
        map.setOnInfoWindowClickListener(this);



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
       map.setOnPolylineClickListener(this);


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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        marker.setSnippet("¿Ver la ruta seleccionada?");

            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(marker.getSnippet())
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            calculateDirections(marker);
                            if(ConductorElegido != null){
                                ConductorElegido= null;

                            }
                            ConductorElegido = marker.getTitle();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }

    private void calculateDirections(Marker marker){
        Log.d(TAG, "calculateDirections: calculating directions.");

        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                marker.getPosition().latitude,
                marker.getPosition().longitude
        );
        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApicontext);

        directions.alternatives(true);
        directions.origin(
                new com.google.maps.model.LatLng(
                        this.loc.getLatitude(),
                        this.loc.getLongitude()
                )
        );
        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
                Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
                Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
                Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
                addPolylinesToMap(result);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage() );

            }
        });
    }

    private void addPolylinesToMap(final DirectionsResult result){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: result routes: " + result.routes.length);
                if(mPolylinesData.size()>0){
                    for (PolylineData polylineData: mPolylinesData){

                        polylineData.getPolyline().remove();
                    }
                    mPolylinesData.clear();
                    mPolylinesData = new ArrayList<>();
                }

                for(DirectionsRoute route: result.routes){
                    Log.d(TAG, "run: leg: " + route.legs[0].toString());
                    List<com.google.maps.model.LatLng> decodedPath = PolylineEncoding.decode(route.overviewPolyline.getEncodedPath());

                    List<LatLng> newDecodedPath = new ArrayList<>();

                    // This loops through all the LatLng coordinates of ONE polyline.
                    for(com.google.maps.model.LatLng latLng: decodedPath){

//                        Log.d(TAG, "run: latlng: " + latLng.toString());

                        newDecodedPath.add(new LatLng(
                                latLng.lat,
                                latLng.lng
                        ));
                    }
                    Polyline polyline = map.addPolyline(new PolylineOptions().addAll(newDecodedPath));
                    polyline.setColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                    polyline.setClickable(true);
                    mPolylinesData.add(new PolylineData(polyline, route.legs[0]));


                }

            }
        });
    }

    @Override
    public void onPolylineClick(Polyline polyline) {

        for(PolylineData polylineData: mPolylinesData){
            Log.d(TAG, "onPolylineClick: toString: " + polylineData.toString());
            if(polyline.getId().equals(polylineData.getPolyline().getId())){
                polylineData.getPolyline().setColor(ContextCompat.getColor(getActivity(), R.color.common_google_signin_btn_text_light_pressed));
                polylineData.getPolyline().setZIndex(1);
                Confirmar.setEnabled(true);
                Confirmar.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                      String inicio =  polylineData.getLeg().startAddress;
                        String[] parts = polylineData.getLeg().startAddress.split(",");
                      String fin = polylineData.getLeg().endAddress;
                        String[] parts2 = polylineData.getLeg().endAddress.split(",");
                      Long tiempo = polylineData.getLeg().duration.inSeconds;
                      String ss = polylineData.getLeg().toString();
                      String Conductor = ConductorElegido;
                      String Pasajero = NombrePasajero;
                      int monto =  2*tiempo.intValue();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://tranquil-sea-18734.herokuapp.com/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .build();
                        final HerokuService service = retrofit.create(HerokuService.class);

                        Call<ResponseBody> call = service.AccesoGeneral("CrearReserva,"+NombrePasajero+","+"123,"+parts[0]+"-"+"27/05/2019-"+"Concepto"+"-"+parts2[0]+"-");
                        System.out.println("CrearReserva,"+NombrePasajero+","+"123,"+parts[0]+"-"+"27/05/2019-"+"Concepto"+"-"+parts2[0]+"-");
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> _,
                                                   Response<ResponseBody> response) {
                                try {

                                    System.out.println(response.body().string());

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
            else{
                polylineData.getPolyline().setColor(ContextCompat.getColor(getActivity(), R.color.common_google_signin_btn_text_light_disabled));
                polylineData.getPolyline().setZIndex(0);
            }
        }
    }
}
