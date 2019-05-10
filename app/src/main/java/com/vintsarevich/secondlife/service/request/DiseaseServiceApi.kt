package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.Disease
import retrofit2.Call
import retrofit2.http.GET

interface DiseaseServiceApi {
    @GET("/disease")
    fun getAllDisease(): Call<List<Disease>>
}