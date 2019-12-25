package com.zawlynn.codigo.assignment.di.module


import com.zawlynn.appsynthcodechallenge.AppSyncthApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationContextModule(app: AppSyncthApplication) {
    val applicaiton:AppSyncthApplication
    init {
        this.applicaiton= app
    }
    @Provides
    @Singleton
    fun provideApplicatioln():AppSyncthApplication{
        return applicaiton
    }
}