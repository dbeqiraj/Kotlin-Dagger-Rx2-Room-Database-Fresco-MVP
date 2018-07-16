package com.dibeqiraj.cakeapp.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dibeqiraj.cakeapp.application.App
import com.dibeqiraj.cakeapp.di.components.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    lateinit var unbinder: Unbinder
    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        unbinder = ButterKnife.bind(this)
        onViewReady(savedInstanceState, intent)
    }

    @CallSuper
    protected open fun onViewReady(savedInstanceState: Bundle?, intent: Intent) = resolveDaggerDependency()

    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }

    protected open fun resolveDaggerDependency() {}

    protected abstract fun getContentView(): Int

    protected fun showBackArrow() {
        val supportActionBar: ActionBar? = supportActionBar

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    protected fun showDialog(message: String) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setMessage(message)
        mProgressDialog.show()
    }

    protected fun hideDialog() {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

    protected fun getApplicationComponent(): ApplicationComponent = (getApplication() as App).mApplicationComponent
}