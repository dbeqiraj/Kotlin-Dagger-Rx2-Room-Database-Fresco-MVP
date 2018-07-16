package com.dibeqiraj.cakeapp.mvp.presenter

import com.dibeqiraj.cakeapp.api.CakeApiService
import com.dibeqiraj.cakeapp.base.BasePresenter
import com.dibeqiraj.cakeapp.database.AppDatabase
import com.dibeqiraj.cakeapp.database.entity.Cake
import com.dibeqiraj.cakeapp.mapper.CakeMapper
import com.dibeqiraj.cakeapp.mvp.model.CakesResponse
import com.dibeqiraj.cakeapp.mvp.view.MainView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CakePresenter @Inject constructor() : BasePresenter<MainView>(), Observer<CakesResponse> {

    @Inject
    protected lateinit var mApiService: CakeApiService
    @Inject
    protected lateinit var mCakeMapper: CakeMapper
    @Inject
    protected lateinit var db: AppDatabase

    override fun onSubscribe(d: Disposable) {}

    fun getCakes() {
        mView.onShowDialog("Loading cakes....")
        val cakesResponseObservable: Observable<CakesResponse> = mApiService.getCakes()
        subscribe(cakesResponseObservable, this)
    }

    fun getCakesFromDatabase() {
        val cakes: MutableList<Cake> = db.cakeDao().getSavedCakes()
        mView.onClearItems()
        mView.onCakeLoaded(cakes)
    }

    override fun onError(e: Throwable) {
        mView.onHideDialog()
        mView.onShowToast("Error loading cakes " + e.message)
    }

    override fun onNext(cakesResponse: CakesResponse) {
        val cakes = mCakeMapper.mapCakes(db, cakesResponse)
        mView.onClearItems()
        mView.onCakeLoaded(cakes)
    }

    override fun onComplete() {
        mView.onHideDialog()
        mView.onShowToast("Cakes loading complete!")
    }
}