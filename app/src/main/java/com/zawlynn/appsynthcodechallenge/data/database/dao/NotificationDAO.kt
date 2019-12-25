package com.zawlynn.appsynthcodechallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo

import io.reactivex.Flowable

@Dao
abstract class NotificationDAO {
    @Query(" SELECT * FROM NotiInfo")
    abstract fun getAllData(): Flowable<List<NotiInfo>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveAllData(movies:List<NotiInfo>)
}