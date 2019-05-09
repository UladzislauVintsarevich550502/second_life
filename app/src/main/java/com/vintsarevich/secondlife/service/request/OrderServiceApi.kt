package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.Order
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderServiceApi {
    @GET("/order")
    fun getAllOrders(): Call<List<Order>>

    @POST("/order")
    fun createOrder(@Query("name") name: String, @Query("doctor") doctor: String): Call<Long>
}