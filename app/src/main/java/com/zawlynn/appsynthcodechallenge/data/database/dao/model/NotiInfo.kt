package com.zawlynn.appsynthcodechallenge.data.database.dao.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotiInfo (val created:String
                     ,@PrimaryKey val text:String)