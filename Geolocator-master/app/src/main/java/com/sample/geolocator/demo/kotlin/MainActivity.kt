package com.sample.geolocator.demo.kotlin

import android.Manifest.permission
import android.os.Bundle
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.sample.geolocator.demo.R
import com.sample.geolocator.geofencer.Geofencer
import com.sample.geolocator.geofencer.models.Geofence
import com.sample.geolocator.tracking.LocationTracker
import net.kibotu.logger.LogcatLogger
import net.kibotu.logger.Logger
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogger(LogcatLogger())

//        requestLocationPermission {
//            if (it.granted) {
//                registerGeofenceUpdates()
//                registerLocationUpdateEvents()
//            }
//        }
    }

    @RequiresPermission(permission.ACCESS_FINE_LOCATION)
    private fun registerGeofenceUpdates() {
        val geofence = Geofence(
            id = UUID.randomUUID().toString(),
            latitude = XXXXXXX,
            longitude = XXXXXXXX,
            radius = 30.0,
            title = "India",
            message = "Entered India",
            transitionType = com.google.android.gms.location.Geofence.GEOFENCE_TRANSITION_ENTER
        )

        Geofencer(this).addGeofence(geofence, GeofenceIntentService::class.java) { /* successfully added geofence */ }
    }

    @RequiresPermission(permission.ACCESS_FINE_LOCATION)
    private fun registerLocationUpdateEvents() {
        LocationTracker.requestLocationUpdates(this, LocationTrackerService::class.java)
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navHost).navigateUp() || super.onSupportNavigateUp()
}
