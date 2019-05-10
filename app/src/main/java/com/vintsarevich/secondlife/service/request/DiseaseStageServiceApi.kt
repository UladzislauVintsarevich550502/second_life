package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.DiseaseStage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiseaseStageServiceApi {

    @GET("/disease_stage")
    fun getDiseaseStages(@Query("orderId") orderId: Long): Call<List<DiseaseStage>>
}