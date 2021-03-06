package com.vintsarevich.secondlife.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")
    @Expose
    val username: String?,

    @SerializedName("password")
    @Expose
    val password: String?
)