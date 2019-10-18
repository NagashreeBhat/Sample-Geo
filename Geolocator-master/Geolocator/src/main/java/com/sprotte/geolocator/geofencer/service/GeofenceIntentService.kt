package com.sample.geolocator.geofencer.service

import android.content.Intent
import androidx.core.app.JobIntentService
import com.sample.geolocator.geofencer.Geofencer
import com.sample.geolocator.geofencer.models.Geofence
import com.sample.geolocator.utils.log

abstract class GeofenceIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        log("onHandleWork $intent")
        val geofence =
            Geofencer.parseExtras(applicationContext, intent)
        if (geofence != null) {
            onGeofence(geofence)
        }
    }

    abstract fun onGeofence(geofence: Geofence)
}