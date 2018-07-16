package com.dibeqiraj.cakeapp.di.module

import com.dibeqiraj.cakeapp.api.CakeApiService
import com.dibeqiraj.cakeapp.di.scope.PerActivity
import com.dibeqiraj.cakeapp.mvp.view.MainView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CakeModule(view: MainView) {

    val mView = view

    @PerActivity
    @Provides
    internal fun provideApiService(retrofit: Retrofit): CakeApiService {
        return retrofit.create(CakeApiService::class.java)
    }

    @PerActivity
    @Provides
    fun provideView(): MainView = mView
}