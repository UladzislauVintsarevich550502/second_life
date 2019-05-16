package com.vintsarevich.secondlife.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Details(

    @SerializedName("orderName")
    @Expose
    var orderName: String,

    @SerializedName("doctorName")
    @Expose
    var doctorName: String,

    @SerializedName("labName")
    @Expose
    var labName: String,

    @SerializedName("patientName")
    @Expose
    var patientName: String,

    @SerializedName("disease")
    @Expose
    var disease: String,

    @SerializedName("testRecommendation")
    @Expose
    var testRecommendation: String,

    @SerializedName("therapy")
    @Expose
    var therapy: String
)