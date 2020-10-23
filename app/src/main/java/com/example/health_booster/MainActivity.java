package com.example.health_booster;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private MapView mapView;
    private GoogleMap googleMap;
    private LatLng latLng;
    private Circle circle;
    private LatLng taskLatLng;
    private Marker marker;
    private SharedPreferences sharedPreferences;
    private LocationManager locationManager;

    //Here use Handler class for continuous location update logic
    private final Handler locationHandler = new Handler();
    private final Runnable locationUpdater = new Runnable() {
        @Override
        public void run() {
            updateLocation();
            //Do every 1 second
            locationHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Data persistence
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        //Update score display from data persistence
        updateCurrentScore(getCurrentScore());

        //MapView Init codes
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle("MapViewBundleKey");
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //MapView codes
        Bundle mapViewBundle = outState.getBundle("MapViewBundleKey");
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle("MapViewBundleKey", mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Init MapView when ready, set GoogleMap to global variable for others methods to access
        this.googleMap = googleMap;
        googleMap.setMinZoomPreference(15);

        //Start of updating location
        locationHandler.postDelayed(locationUpdater, 0);
    }

    protected void updateLocation() {
        //Core App logic
        //Check if GPS permission is granted. If not granted, request for it
        //If GPS permission is never granted, the user's location cannot be updated
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            //Get device's current location by Google Service API
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
            Task<Location> locationResult = LocationServices.getFusedLocationProviderClient(this).getLastLocation();
            //Async get location, need use callback to handle
            locationResult.addOnCompleteListener(this, (OnCompleteListener<Location>) task -> {
                if (task.isSuccessful()) {
                    //Location can be gotten successfully
                    if (task.getResult() != null)
                    {
                        // Remove last user's location if exist
                        if (circle != null)
                        {
                            circle.remove();
                        }
                        latLng = new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude());
                        //Add back user's location circle mark
                        circle = googleMap.addCircle(new CircleOptions()
                                .center(latLng)
                                .radius(30)
                                .strokeColor(Color.RED)
                                .fillColor(Color.BLUE));
                        //Center user's location
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        //If no tasks yet, generate one
                        if (taskLatLng == null)
                        {
                            updateTask(null);
                        }
                        //Else check if the task is completed
                        else if (Math.abs(taskLatLng.longitude - latLng.longitude) < 0.00050 && Math.abs(taskLatLng.latitude - latLng.latitude) < 0.00050)
                        {
                            //Update score
                            updateCurrentScore(getCurrentScore() + 1);
                            //Generate another task
                            updateTask(null);
                            //Notify user for task completions
                            Toast.makeText(getApplicationContext(), getString(R.string.task_completed), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
        else
        {
            //Check if GPS permission is granted. If not granted, request for it
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    public void updateTask(View view)
    {
        //Generating tasks requires user's current location so check for it first
        if (latLng != null)
        {
            //Generate random location, there will be a bonus for users seldom when the task is too close to current location
            taskLatLng = new LatLng(Math.random()*0.00500*(1 - (int)(Math.random()*3)) + latLng.latitude,
                    Math.random()*0.00500*(1 - (int)(Math.random()*3)) + latLng.longitude);
            //Force replace current task
            if (marker != null)
            {
                marker.remove();
            }
            marker = googleMap.addMarker(new MarkerOptions().position(taskLatLng));
        }
    }

    protected int getCurrentScore()
    {
        // Get score from data persistence
        return sharedPreferences.getInt("totalScore", 0);
    }

    protected void updateCurrentScore(int newScore)
    {
        //Update new score to data persistence
        sharedPreferences.edit().putInt("totalScore", newScore).commit();
        //Also update the UI
        ((TextView)findViewById(R.id.textView)).setText(
                ((TextView)findViewById(R.id.textView)).getText().toString()
                        .replaceAll(":.*+", ": " + newScore));
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public LatLng getTaskLatLng() {
        return taskLatLng;
    }

    public void setTaskLatLng(LatLng taskLatLng) {
        this.taskLatLng = taskLatLng;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Handler getLocationHandler() {
        return locationHandler;
    }

    public Runnable getLocationUpdater() {
        return locationUpdater;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}