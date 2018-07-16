package com.dibeqiraj.cakeapp.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Cake(

        @PrimaryKey val id: Int,
        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "preview_description")
        val previewDescription: String,

        @ColumnInfo(name = "detail_description")
        val detailDescription: String,

        @ColumnInfo(name = "image_url")
        val imageUrl: String)

    : Serializable {

}