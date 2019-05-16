package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.TestRecommendation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestRecommendationServiceApi {

    @GET("/test_recommendation")
    fun getTestRecommendations(@Query("orderId") orderId: Long): Call<List<TestRecommendation>>
}