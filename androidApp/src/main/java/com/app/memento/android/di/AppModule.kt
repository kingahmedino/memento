package com.app.memento.android.di

import android.app.Application
import com.app.memento.android.location.DefaultLocationClient
import com.app.memento.android.location.LocationClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocationClient(application: Application): LocationClient {
        return DefaultLocationClient(
            application,
            LocationServices.getFusedLocationProviderClient(application)
        )
    }

}