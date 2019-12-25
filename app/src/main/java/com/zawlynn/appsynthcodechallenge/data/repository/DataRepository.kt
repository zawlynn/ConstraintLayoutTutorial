package com.zawlynn.appsynthcodechallenge.data.repository

import android.content.Context
import com.zawlynn.appsynthcodechallenge.AppSyncthApplication
import com.zawlynn.appsynthcodechallenge.data.database.dao.NotificationDAO
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.UserInfo
import com.zawlynn.appsynthcodechallenge.data.network.NetworkBoundResource
import com.zawlynn.appsynthcodechallenge.data.network.Resource
import com.zawlynn.appsynthcodechallenge.data.network.api.ApiService
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class DataRepository {
    @Inject
    lateinit var api: ApiService
    @Inject
    lateinit var dao: NotificationDAO

    companion object {
        val instance = DataRepository()

    }
    fun getUserInfo(): Flowable<UserInfo> {
        return api.getUserInfo()
    }
    fun getDatFromApi(context: Context,userId : String): Flowable<Resource<List<NotiInfo>>> {
        return object :  NetworkBoundResource<List<NotiInfo>, List<NotiInfo>>(context) {
            override fun saveCallResult(request: List<NotiInfo>) {
                return dao.saveAllData(request)
            }

            override fun loadFromDb(): Flowable<List<NotiInfo>> {
               return dao.getAllData()
            }

            override fun createCall(): Flowable<Response<List<NotiInfo>>> {
               return api.getDataFromApi(userId)
            }

        }.asFlowable()
    }

    init {
        AppSyncthApplication.INSTANCE.appComponent.inject(this)
    }
}