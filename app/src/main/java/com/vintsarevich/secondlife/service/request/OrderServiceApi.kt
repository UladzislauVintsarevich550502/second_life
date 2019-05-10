package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.Order
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface OrderServiceApi {
    @GET("/order")
    fun getAllOrders(): Call<List<Order>>

    @GET("/order/new")
    fun createOrder(@Query("name") name: String, @Query("doctor") doctor: String): Call<Long>

    @PUT("/order/survey/disease")
    fun addDiseaseToOrder(@Query("orderId") orderId: Long, @Query("diseaseId") diseaseId: Long): Call<Void>

    @PUT("/order/survey/disease_stage")
    fun addDiseaseStageToOrder(@Query("orderId") orderId: Long, @Query("diseaseId") diseaseId: Long): Call<Void>
}