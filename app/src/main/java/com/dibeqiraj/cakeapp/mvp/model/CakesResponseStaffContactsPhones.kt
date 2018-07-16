package com.dibeqiraj.cakeapp.mvp.model

import com.google.gson.annotations.SerializedName

data class CakesResponseStaffContactsPhones(

        @SerializedName("mobile")
        val mobile: String,

        @SerializedName("home")
        val home: String

)