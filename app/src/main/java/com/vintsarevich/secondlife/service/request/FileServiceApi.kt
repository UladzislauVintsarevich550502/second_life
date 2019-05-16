package com.vintsarevich.secondlife.service.request

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FileServiceApi {

    @GET("/file")
    fun resendOrder(@Query("orderId") orderId: Long): Call<Boolean>
}