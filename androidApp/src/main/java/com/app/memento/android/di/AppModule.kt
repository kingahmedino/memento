package com.app.memento.android.di

import android.app.Application
import com.app.memento.android.location.DefaultLocationClient
import com.app.memento.android.location.LocationClient
import com.app.memento.android.utils.DataStoreUtils
import com.app.memento.data.DatabaseDriverFactory
import com.app.memento.database.MementoDb
import com.app.memento.domain.reminder.ReminderDAO
import com.app.memento.domain.reminder.ReminderDAOImpl
import com.google.android.gms.location.LocationServices
import com.squareup.sqldelight.db.SqlDriver
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

    @Provides
    @Singleton
    fun provideDataStore(application: Application): DataStoreUtils {
        return DataStoreUtils(application)
    }

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideReminderDAO(driver: SqlDriver): ReminderDAO {
        return ReminderDAOImpl(MementoDb(driver))
    }

}