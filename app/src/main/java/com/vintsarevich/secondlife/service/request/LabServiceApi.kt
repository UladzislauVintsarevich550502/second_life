package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.Lab
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LabServiceApi {

    @GET("/lab")
    fun getLabs(@Query("orderId") orderId: Long): Call<List<Lab>>
}