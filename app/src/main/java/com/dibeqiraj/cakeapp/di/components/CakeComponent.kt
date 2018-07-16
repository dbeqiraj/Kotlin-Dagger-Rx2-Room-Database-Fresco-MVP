package com.dibeqiraj.cakeapp.di.components

import com.dibeqiraj.cakeapp.di.module.ApplicationModule
import com.dibeqiraj.cakeapp.di.module.CakeModule
import com.dibeqiraj.cakeapp.di.scope.PerActivity
import com.dibeqiraj.cakeapp.modules.home.MainActivity
import com.dibeqiraj.cakeapp.mvp.view.MainView
import dagger.Component

@PerActivity
@Component(modules = arrayOf(CakeModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface CakeComponent {
    fun inject(activity: MainActivity)
}