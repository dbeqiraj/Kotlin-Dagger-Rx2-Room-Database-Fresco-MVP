package com.dibeqiraj.cakeapp.mvp.model

import com.google.gson.annotations.SerializedName

data class CakesResponseStaffContacts(

        @SerializedName("role")
        val role: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("phones")
        val phones: CakesResponseStaffContactsPhones,

        @SerializedName("date_of_birth")
        val dateOfBirth: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("email")
        val email: Array<String>
)