package com.dibeqiraj.cakeapp.di.components

import android.content.Context
import com.dibeqiraj.cakeapp.database.AppDatabase
import com.dibeqiraj.cakeapp.di.module.ApplicationModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun exposeRetrofit(): Retrofit
    fun exposeContext(): Context
    fun exposeDatabase(): AppDatabase
}