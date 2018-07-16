package com.dibeqiraj.cakeapp.mvp.view

import com.dibeqiraj.cakeapp.database.entity.Cake

interface MainView : BaseView {
    fun onCakeLoaded(cakes: MutableList<Cake>)
    fun onShowDialog(message: String)
    fun onHideDialog()
    fun onShowToast(message: String)
    fun onClearItems()
}