
![
     
### Requirmenets

1. Location permissions in [*AndroidManifest.xml*](app/src/main/AndroidManifest.xml#L8-L9)

	    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
   	 	<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
   	 	
2. Google maps api key

		<string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">YOUR_KEY</string>
	
### How to use

### Geofence

1. Create [Receiver](app/src/main/java/com/sample/geolocator/demo/kotlin/GeofenceIntentService.kt)

```kotlin
class GeofenceIntentService : GeofenceIntentService() {
	
    override fun onGeofence(geofence: Geofence) {
    	Log.v(GeoFenceIntentService::class.java.simpleName, "onGeofence $geofence")	    
    }
}
```		
2. Add receiver to your [manifest](app/src/main/AndroidManifest.xml#L45-L47)

	 	<service
            android:name=".kotlin.GeoFenceIntentService"
            android:permission="android.permission.BIND_JOB_SERVICE" />

3. [Start geofence tracking](app/src/main/java/com/sample/geolocator/demo/kotlin/MainActivity.kt#L33-L46)

```kotlin
val geofence = Geofence(
    id = UUID.randomUUID().toString(),
    latitude = XXXXXX,
    longitude = XXXXXX,
    radius = 30.0,
    title = "India",
    message = "Entered India",
    transitionType = GEOFENCE_TRANSITION_ENTER
)
    
Geofencer(this).addGeofence(geofence, GeoFenceIntentService::class.java) { /* successfully added geofence */ }
```
### Location Tracker

### How to use in Java

### Geofence

1. Create [Receiver](app/src/main/java/com/sample/geolocator/demo/java/GeofenceIntentService.java)

```java
public class GeoFenceIntentService extends GeofenceIntentService {
	
	@Override
	public void onGeofence(@NotNull Geofence geofence) {
	
    	Log.v(GeoFenceIntentService.class.getSimpleName(), "onGeofence " + geofence);	    	
   	}
}
```		
2. Add receiver to your [manifest](app/src/main/AndroidManifest.xml#L63-L65)

	 	<service
            android:name=".java.GeoFenceIntentService"
            android:permission="android.permission.BIND_JOB_SERVICE" />

3. [Start geofence tracking](app/src/main/java/com/sample/geolocator/demo/java/AddGeoFenceActivity.java#L48-L63)

```java
Geofence geofence = new Geofence(
        UUID.randomUUID().toString(),
        longitute#,
        latitude#,
        30.0,
        "India",
        "Entered India",
        GEOFENCE_TRANSITION_ENTER);
Geofencer geofencer = new Geofencer(this);
geofencer.addGeofence(geofence, GeoFenceIntentService.class,
   	 () -> /* successfully added geofence */ Unit.INSTANCE);        	 
```
### Location Tracker

1. Create [Receiver](app/src/main/java/com/sample/geolocator/demo/java/LocationTrackerService.java)

```java
public class LocationTrackerService extends LocationTrackerUpdateIntentService {

    @Override
    public void onLocationResult(@NotNull LocationResult location) {
	
        Log.v(GeoFenceIntentService.class.getSimpleName(), "onLocationResult " + location);		        );
    }
}
```	

2. Add receiver to [manifest](app/src/main/AndroidManifest.xml#L66-L68)

		<service
            android:name=".java.LocationTrackerService"
            android:permission="android.permission.BIND_JOB_SERVICE" />

3. [Start tracking](https://github.com/exozet/Geolocator/blob/master/app/src/main/java/com/sample/geolocator/demo/java/AddGeoFenceActivity.java#L65-L68)

```java
LocationTracker.INSTANCE.requestLocationUpdates(this, LocationTrackerService.class);
```

4. Stop tracking

```java
LocationTracker.INSTANCE.removeLocationUpdates(this);
```

### How to install

#### jCenter / mavenCentral

	implementation 'com.sample:Geolocator:latest'



##### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
##### Step 2. Add the dependency

	dependencies {
		implementation 'com.github.exozet:Geolocator:latest'
	}
	
### Configuration

Default Location tracking update intervals can be overriden, by adding following parameter into your _app/res/_ - folder, e.g. [**app/res/config.xml**](app/src/main/res/values/config.xml#L4-L7)

    <integer name="location_update_interval_in_millis">0</integer>
    <integer name="location_fastest_update_interval_in_millis">0</integer>
    <integer name="location_max_wait_time_interval_in_millis">0</integer>
    <integer name="location_min_distance_for_updates_in_meters">0</integer>

### Known Issues

- does not work when in doze mode [#2](https://github.com/exozet/Geolocator/issues/2)



