package com.dibeqiraj.cakeapp.application

import android.app.Application
import com.dibeqiraj.cakeapp.di.components.ApplicationComponent
import com.dibeqiraj.cakeapp.di.components.DaggerApplicationComponent
import com.dibeqiraj.cakeapp.di.module.ApplicationModule
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    val mApplicationComponent: ApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this, "https://gist.githubusercontent.com"))
            .build()

    override fun onCreate() {
        super.onCreate()
        initializeFresco()
        initializeLeakcanary()
    }

    fun initializeLeakcanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    fun initializeFresco() {
        val config: ImagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)
    }

}