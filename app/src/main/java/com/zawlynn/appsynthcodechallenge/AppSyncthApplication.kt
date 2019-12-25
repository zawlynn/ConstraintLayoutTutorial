package com.zawlynn.appsynthcodechallenge

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.zawlynn.appsynthcodechallenge.constants.USER_INFORMATION
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.UserInfo
import com.zawlynn.appsynthcodechallenge.di.component.DaggerDataComponent
import com.zawlynn.appsynthcodechallenge.di.component.DataComponent
import com.zawlynn.codigo.assignment.di.module.ApplicationContextModule

class AppSyncthApplication : Application() {
     lateinit var appComponent: DataComponent
    lateinit var prefs: SharedPreferences
    private val MyPREFERENCES = "UserData"
    companion object {
        lateinit var INSTANCE: AppSyncthApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = getApplicationComponent()
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)

    }

     fun getApplicationComponent(): DataComponent =
        DaggerDataComponent.builder().applicationContextModule(ApplicationContextModule(this)).build()


    fun saveStringValueInSP(key: String, value: String) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringValueInSP(key: String): String? {
        return prefs.getString(key, "")
    }
}