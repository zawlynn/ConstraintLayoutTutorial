package com.zawlynn.appsynthcodechallenge.data.network.api

import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.UserInfo
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("razir/users/{userId}/notifications")
    fun getDataFromApi(@Path("userId") userId:String): Flowable<Response<List<NotiInfo>>>

    @GET("razir/user/profile")
    fun getUserInfo():Flowable<UserInfo>
}