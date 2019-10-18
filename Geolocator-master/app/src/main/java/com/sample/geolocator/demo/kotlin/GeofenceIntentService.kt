package com.sample.geolocator.demo.kotlin

import android.util.Log
import com.sample.geolocator.demo.misc.sendNotification
import com.sample.geolocator.geofencer.models.Geofence


class GeofenceIntentService : com.sample.geolocator.geofencer.service.GeofenceIntentService() {

    override fun onGeofence(geofence: Geofence) {
        Log.v(com.sample.geolocator.demo.java.GeofenceIntentService::class.java.simpleName, "onGeofence $geofence")
        sendNotification(
            applicationContext,
            geofence.title,
            geofence.message
        )
    }
}