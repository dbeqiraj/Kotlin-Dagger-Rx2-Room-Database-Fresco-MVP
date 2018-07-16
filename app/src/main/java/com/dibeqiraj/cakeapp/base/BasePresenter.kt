package com.dibeqiraj.cakeapp.base

import com.dibeqiraj.cakeapp.mvp.view.BaseView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class BasePresenter<V : BaseView> {

    @Inject
    protected lateinit var mView: V

    protected fun <T> subscribe(observable: Observable<T>, observer: Observer<T>) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}