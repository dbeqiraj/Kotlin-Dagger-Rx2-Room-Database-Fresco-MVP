package com.dibeqiraj.cakeapp.modules.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dibeqiraj.cakeapp.R
import com.dibeqiraj.cakeapp.database.entity.Cake
import com.facebook.drawee.view.SimpleDraweeView

class CakeAdapter constructor(layoutInflater: LayoutInflater) : RecyclerView.Adapter<CakeAdapter.Holder>() {

    val mCakeList: MutableList<Cake> = ArrayList()
    val mLayoutInflater: LayoutInflater = layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = mCakeList.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(mCakeList[position])

    fun addCakes(cakes: MutableList<Cake>) {
        mCakeList.addAll(cakes)
        notifyDataSetChanged()
    }

    fun clearCakes() {
        mCakeList.clear()
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        @BindView(R.id.cake_icon)
        protected lateinit var mCakeIcon: SimpleDraweeView
        @BindView(R.id.textview_title)
        protected lateinit var mCakeTitle: TextView
        @BindView(R.id.textview_preview_description)
        protected lateinit var mCakePreviewDescription: TextView

        var mCake: Cake? = null

        init {
            itemView.setOnClickListener(this)
            ButterKnife.bind(this, itemView)
        }

        fun bind(cake: Cake) {
            mCake = cake
            mCakeTitle.setText(cake.title)
            mCakePreviewDescription.setText(cake.previewDescription)
            mCakeIcon.setImageURI(cake.imageUrl)
        }

        override fun onClick(v: View?) {
            mCakeClickListener?.onClick(mCakeIcon, mCake, getAdapterPosition())
        }

    }

    var mCakeClickListener: OnCakeClickListener? = null

    interface OnCakeClickListener {
        fun onClick(v: SimpleDraweeView?, cake: Cake?, postition: Int)
    }
}