package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.Therapy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TherapyServiceApi {

    @GET("/therapy")
    fun getTherapies(@Query("orderId") orderId: Long): Call<List<Therapy>>
}