package com.sample.geolocator.demo.java;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.sample.geolocator.demo.R;
import com.sample.geolocator.demo.misc.UtilsKt;
import com.sample.geolocator.geofencer.Geofencer;
import com.sample.geolocator.geofencer.models.Geofence;
import com.sample.geolocator.tracking.LocationTracker;

import net.kibotu.logger.Level;
import net.kibotu.logger.LogcatLogger;
import net.kibotu.logger.Logger;

import java.util.UUID;

import kotlin.Unit;

import static com.google.android.gms.location.Geofence.GEOFENCE_TRANSITION_ENTER;

public class AddGeoFenceActivity extends AppCompatActivity {

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Logger.addLogger(new LogcatLogger(), Level.VERBOSE);

        UtilsKt.requestLocationPermission(this, permission ->
        {
            if (permission.granted) {
                registerGeofenceUpdates();
                registerLocationUpdateEvents();
            }
            return Unit.INSTANCE;
        });
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void registerGeofenceUpdates() {

        Geofence geofence = new Geofence(
                UUID.randomUUID().toString(),
                XXXXXXX,
                XXXXXXX,
                30.0,
                "India",
                "Entered India",
                GEOFENCE_TRANSITION_ENTER);
        Geofencer geofencer = new Geofencer(this);
        geofencer.addGeofence(geofence, GeofenceIntentService.class,
                () -> /* successfully added geofence */ Unit.INSTANCE);

    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void registerLocationUpdateEvents() {
        LocationTracker.INSTANCE.requestLocationUpdates(this, LocationTrackerService.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.navHost).navigateUp() || super.onSupportNavigateUp();
    }
}