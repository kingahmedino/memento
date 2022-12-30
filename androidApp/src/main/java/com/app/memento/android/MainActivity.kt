package com.app.memento.android

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.app.memento.android.other.GeofenceHelper
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var geofencingClient: GeofencingClient
    private lateinit var geofenceHelper: GeofenceHelper
    private val GEOFENCE_ID = "id"

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        geofencingClient = LocationServices.getGeofencingClient(this)
        geofenceHelper = GeofenceHelper(this)

        setContent {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            MyApplicationTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Button(onClick = {
                        val geofence = geofenceHelper.getGeofence(
                            GEOFENCE_ID,
                            2000F,
                            Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT or Geofence.GEOFENCE_TRANSITION_DWELL
                        )
                        val geofencingRequest = geofenceHelper.getGeofencingRequest(geofence)
                        val pendingIntent = geofenceHelper.geofencePendingIntent

                        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                            .addOnSuccessListener {
                                println("onSuccess: Geofence Added...")
                            }
                            .addOnFailureListener { e ->
                                val errorMessage = geofenceHelper.getErrorString(e)
                                println("Error: $errorMessage")
                            }
                    }) {
                        Text(text = "Add geofence")
                    }

                    Button(onClick = { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                            0
                        )
                    } }) {
                        Text(text = "Get bg permission")
                    }
                }
            }
        }
    }
}