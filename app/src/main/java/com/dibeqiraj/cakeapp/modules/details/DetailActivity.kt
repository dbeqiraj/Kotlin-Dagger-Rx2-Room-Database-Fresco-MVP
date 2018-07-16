package com.dibeqiraj.cakeapp.modules.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import com.dibeqiraj.cakeapp.R
import com.dibeqiraj.cakeapp.base.BaseActivity
import com.dibeqiraj.cakeapp.database.entity.Cake
import com.facebook.drawee.view.SimpleDraweeView

class DetailActivity : BaseActivity() {

    companion object {
        val CAKE: String = "cake"
    }

    @BindView(R.id.cakeImage)
    protected lateinit var mCakeImage: SimpleDraweeView
    @BindView(R.id.cakeTitle)
    protected lateinit var mCakeTitle: TextView
    @BindView(R.id.cakeDescription)
    protected lateinit var cakeDescription: TextView

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)

        showBackArrow()

        val cake: Cake = intent.getSerializableExtra(CAKE) as Cake
        setTitle("Cake Detail")

        mCakeTitle.text = cake.title
        cakeDescription.text = cake.detailDescription
        mCakeImage.setImageURI(cake.imageUrl)
    }

    override fun getContentView(): Int = R.layout.activity_detail

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}