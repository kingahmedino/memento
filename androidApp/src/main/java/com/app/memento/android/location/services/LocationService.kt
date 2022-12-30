package com.app.memento.android.location.services

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.R
import com.app.memento.android.location.LocationClient
import com.app.memento.android.utils.Constants.LOCATION_TRACKING_FOREGROUND_ID
import com.app.memento.android.utils.Constants.LOCATION_TRACKING_NOTIFICATION_CHANNEL_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var locationClient: LocationClient

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification =
            NotificationCompat.Builder(this, LOCATION_TRACKING_NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Tracking location...")
                .setContentText("Location: null")
                .setOngoing(true)
                .setSmallIcon(R.drawable.notification_bg)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(10000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val latitude = location.latitude.toString()
                val longitude = location.longitude.toString()
                val updatedNotification =
                    notification.setContentText("Location: ($latitude, $longitude)")
                notificationManager.notify(
                    LOCATION_TRACKING_FOREGROUND_ID,
                    updatedNotification.build()
                )
            }.launchIn(serviceScope)

        startForeground(LOCATION_TRACKING_FOREGROUND_ID, notification.build())
    }

    private fun stop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            stopForeground(STOP_FOREGROUND_DETACH)
        else
            stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}