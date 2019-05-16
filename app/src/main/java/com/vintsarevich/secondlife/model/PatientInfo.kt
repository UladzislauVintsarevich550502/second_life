package com.vintsarevich.secondlife.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PatientInfo(

    @SerializedName("dateOfBirthday")
    @Expose
    val dateOfBirthday: String,

    @SerializedName("gender")
    @Expose
    val gender: String,

    @SerializedName("firstName")
    @Expose
    val firstName: String,

    @SerializedName("secondName")
    @Expose
    val secondName: String,

    @SerializedName("address")
    @Expose
    val address: String
)