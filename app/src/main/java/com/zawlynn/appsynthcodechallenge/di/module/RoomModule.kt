package com.zawlynn.codigo.assignment.di.module


import androidx.room.Room
import com.zawlynn.appsynthcodechallenge.AppSyncthApplication
import com.zawlynn.appsynthcodechallenge.data.database.AppsynthDatabase
import com.zawlynn.appsynthcodechallenge.data.database.dao.NotificationDAO
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provoideDao(database: AppsynthDatabase): NotificationDAO {
        return database.dao()
    }

    @Provides
    fun provideDatabase(application: AppSyncthApplication): AppsynthDatabase {
        return Room.databaseBuilder(application, AppsynthDatabase::class.java,"data.db")
            .fallbackToDestructiveMigration().build()
    }
}