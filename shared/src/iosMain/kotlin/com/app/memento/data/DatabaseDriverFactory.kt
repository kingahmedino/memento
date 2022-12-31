package com.app.memento.data

import com.app.memento.database.MementoDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MementoDb.Schema, "MementoDb")
    }
}