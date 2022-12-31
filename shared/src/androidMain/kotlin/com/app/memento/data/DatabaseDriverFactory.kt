package com.app.memento.data

import android.content.Context
import com.app.memento.database.MementoDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MementoDb.Schema, context, "MementoDb")
    }
}