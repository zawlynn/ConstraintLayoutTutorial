package com.zawlynn.appsynthcodechallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zawlynn.appsynthcodechallenge.data.database.dao.NotificationDAO
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo


@Database(entities = [NotiInfo::class], version = 1, exportSchema = false)
abstract class AppsynthDatabase : RoomDatabase() {
    abstract fun dao(): NotificationDAO
}