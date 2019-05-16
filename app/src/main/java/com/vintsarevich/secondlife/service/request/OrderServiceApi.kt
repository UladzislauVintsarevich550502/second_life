package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.Details
import com.vintsarevich.secondlife.model.Order
import com.vintsarevich.secondlife.model.PatientInfo
import retrofit2.Call
import retrofit2.http.*

interface OrderServiceApi {
    @GET("/order")
    fun getAllOrders(): Call<List<Order>>

    @GET("/order/{id}")
    fun getDetails(@Path("id") id: Long): Call<Details>

    @GET("/order/new")
    fun createOrder(@Query("name") name: String, @Query("doctor") doctor: String): Call<Long>

    @PUT("/order/survey/disease")
    fun addDiseaseToOrder(@Query("orderId") orderId: Long, @Query("diseaseId") diseaseId: Long): Call<Void>

    @PUT("/order/survey/disease_stage")
    fun addDiseaseStageToOrder(@Query("orderId") orderId: Long, @Query("diseaseStageId") diseaseStageId: Long): Call<Void>

    @PUT("/order/survey/lab")
    fun addLabToOrder(@Query("orderId") orderId: Long, @Query("labId") labId: Long): Call<Void>

    @PUT("/order/survey/test_recommendation")
    fun addTestRecommendationToOrder(@Query("orderId") orderId: Long, @Query("testRecommendationId") testRecommendationId: Long): Call<Void>

    @PUT("/order/survey/therapy")
    fun addTherapyToOrder(@Query("orderId") orderId: Long, @Query("therapyId") therapyId: Long): Call<Void>

    @POST("/order/submit_order")
    fun submitOrder(@Query("orderId") orderId: Long, @Body patientInfo: PatientInfo): Call<Boolean>
}