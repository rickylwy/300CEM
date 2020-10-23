package com.example.health_booster;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private LatLng latLng;
    private Circle circle;
    private LatLng taskLatLng;
    private Marker marker;
    private SharedPreferences sharedPreferences;

    private final Handler locationHandler = new Handler();
    private final Runnable locationUpdater = new Runnable() {
        @Override
        public void run() {
            updateLocation();
            locationHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        updateCurrentScore(getCurrentScore());

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle("MapViewBundleKey");
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

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
        this.googleMap = googleMap;
        googleMap.setMinZoomPreference(15);

        locationHandler.postDelayed(locationUpdater, 0);
    }

    protected void updateLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Task<Location> locationResult = LocationServices.getFusedLocationProviderClient(this).getLastLocation();
            locationResult.addOnCompleteListener(this, (OnCompleteListener<Location>) task -> {
                if (task.isSuccessful()) {
                    // Set the map's camera position to the current location of the device.
                    if (circle != null)
                    {
                        circle.remove();
                    }
                    if (task.getResult() != null)
                    {
                        latLng = new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude());
                        circle = googleMap.addCircle(new CircleOptions()
                                .center(latLng)
                                .radius(30)
                                .strokeColor(Color.RED)
                                .fillColor(Color.BLUE));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        if (taskLatLng == null)
                        {
                            updateTask(null);
                        }
                        else if (Math.abs(taskLatLng.longitude - latLng.longitude) < 0.00050 && Math.abs(taskLatLng.latitude - latLng.latitude) < 0.00050)
                        {
                            updateCurrentScore(getCurrentScore() + 1);
                            updateTask(null);
                            Toast.makeText(getApplicationContext(), getString(R.string.task_completed), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    public void updateTask(View view)
    {
        if (latLng != null)
        {
            taskLatLng = new LatLng(Math.random()*0.00500*(1 - (int)(Math.random()*3)) + latLng.latitude,
                    Math.random()*0.00500*(1 - (int)(Math.random()*3)) + latLng.longitude);
            if (marker != null)
            {
                marker.remove();
            }
            marker = googleMap.addMarker(new MarkerOptions().position(taskLatLng));
        }
    }

    protected int getCurrentScore()
    {
        return sharedPreferences.getInt("totalScore", 0);
    }

    protected void updateCurrentScore(int newScore)
    {
        sharedPreferences.edit().putInt("totalScore", newScore).commit();
        ((TextView)findViewById(R.id.textView)).setText(
                ((TextView)findViewById(R.id.textView)).getText().toString()
                        .replaceAll(":.*", ": " + newScore));
    }
}