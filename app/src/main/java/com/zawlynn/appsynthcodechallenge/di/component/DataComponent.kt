package com.zawlynn.appsynthcodechallenge.di.component


import com.zawlynn.appsynthcodechallenge.data.repository.DataRepository
import com.zawlynn.codigo.assignment.di.module.ApplicationContextModule
import com.zawlynn.codigo.assignment.di.module.RetrofitModule
import com.zawlynn.codigo.assignment.di.module.RoomModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, ApplicationContextModule::class, RoomModule::class])
interface DataComponent {
    fun inject(repository: DataRepository )
}