package com.dibeqiraj.cakeapp.mvp.model

import com.google.gson.annotations.SerializedName

data class CakesResponseCakes(

        @SerializedName("detailDescription")
        val detailDescription: String,

        @SerializedName("image")
        val image: String,

        @SerializedName("previewDescription")
        val previewDescription: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String

)