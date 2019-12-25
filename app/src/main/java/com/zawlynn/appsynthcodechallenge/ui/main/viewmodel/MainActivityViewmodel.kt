package com.zawlynn.appsynthcodechallenge.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.zawlynn.appsynthcodechallenge.AppSyncthApplication
import com.zawlynn.appsynthcodechallenge.constants.USER_INFORMATION
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.UserInfo
import com.zawlynn.appsynthcodechallenge.data.network.Resource
import com.zawlynn.appsynthcodechallenge.data.network.isNetworkStatusAvailable
import com.zawlynn.appsynthcodechallenge.data.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainActivityViewmodel(application: Application) : AndroidViewModel(application) {
    var repository: DataRepository
    val compositeDisposable = CompositeDisposable()
    val userInfo = MutableLiveData<UserInfo>()
    val isNetwork = MutableLiveData<Boolean>()
    val gson = Gson()
    val status = MutableLiveData<Resource.Status>()
    val data = MutableLiveData<List<NotiInfo>>()

    init {
        repository = DataRepository.instance
        val str_userinfo = AppSyncthApplication.INSTANCE.getStringValueInSP(USER_INFORMATION)
        if (str_userinfo.isNullOrEmpty()) {
            if (application.isNetworkStatusAvailable()) {
                getUserInfo()
            } else {
                isNetwork.postValue(false)
            }
        } else {
            userInfo.postValue(gson.fromJson(str_userinfo, UserInfo::class.java))
        }
    }

    fun getUserInfo() {
        status.postValue( Resource.Status.LOADING)
        repository.getUserInfo().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it?.let {
                    userInfo.postValue(it)
                    AppSyncthApplication.INSTANCE
                        .saveStringValueInSP(USER_INFORMATION, gson.toJson(it))
                }
            }.addTo(compositeDisposable)
    }

    fun getDataFromApi(userId: String) {
        repository.getDatFromApi(getApplication(), userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.status == Resource.Status.SUCCESS) {
                    it.data?.let {
                        data.postValue(it)
                    }

                }
                status.postValue(it.status)
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}