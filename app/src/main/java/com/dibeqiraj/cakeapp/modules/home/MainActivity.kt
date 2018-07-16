package com.dibeqiraj.cakeapp.modules.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.BindView
import com.dibeqiraj.cakeapp.R
import com.dibeqiraj.cakeapp.base.BaseActivity
import com.dibeqiraj.cakeapp.database.entity.Cake
import com.dibeqiraj.cakeapp.di.components.DaggerCakeComponent
import com.dibeqiraj.cakeapp.di.module.CakeModule
import com.dibeqiraj.cakeapp.modules.details.DetailActivity
import com.dibeqiraj.cakeapp.modules.home.adapter.CakeAdapter
import com.dibeqiraj.cakeapp.mvp.presenter.CakePresenter
import com.dibeqiraj.cakeapp.mvp.view.MainView
import com.dibeqiraj.cakeapp.utilities.NetworkUtils
import com.facebook.drawee.view.SimpleDraweeView
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @BindView(R.id.cake_list)
    protected lateinit var mCakeList: RecyclerView
    @Inject
    protected lateinit var mPresenter: CakePresenter
    var mCakeAdapter: CakeAdapter? = null


    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)
        initializeList()
        loadCakes()
    }

    fun loadCakes() {
        if (NetworkUtils.isNetAvailable(this)) {
            mPresenter.getCakes()
        } else {
            mPresenter.getCakesFromDatabase()
        }
    }

    fun initializeList() {
        mCakeList.setHasFixedSize(true)
        mCakeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mCakeAdapter = CakeAdapter(layoutInflater)
        mCakeAdapter?.mCakeClickListener = mCakeClickListener
        mCakeList.adapter = mCakeAdapter
    }

    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.action_reload -> {
                loadCakes()
                return true
            }
            R.id.action_about -> {
                showAbout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showAbout() {
        val dialog = AlertDialog.Builder(this)
                .setMessage("Kotlin-Dagger-Rx-AppDatabase-Fresco-MVP. \n\nGet the code and follow me on github!")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok) { dialog, which -> dialog.dismiss() }
                .setNegativeButton("Get Code") { dialog, which ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://github.com/dbeqiraj/Kotlin-Dagger-Rx2-Room-Database-Fresco-MVP")
                    startActivity(intent)
                    dialog.dismiss()
                }
                .create()
        dialog.show()
    }

    override fun resolveDaggerDependency() {
        DaggerCakeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .cakeModule(CakeModule(this))
                .build().inject(this)
    }

    override fun onCakeLoaded(cakes: MutableList<Cake>) {
        mCakeAdapter?.addCakes(cakes)
    }

    override fun onShowDialog(message: String) = showDialog(message)

    override fun onHideDialog() = hideDialog()

    override fun onShowToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun onClearItems() {
        mCakeAdapter?.clearCakes()
    }

    val mCakeClickListener = object : CakeAdapter.OnCakeClickListener {
        override fun onClick(v: SimpleDraweeView?, cake: Cake?, postition: Int) {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CAKE, cake)
            startActivity(intent)
        }
    }
}
