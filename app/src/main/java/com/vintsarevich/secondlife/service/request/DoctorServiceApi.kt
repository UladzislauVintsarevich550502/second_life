package com.vintsarevich.secondlife.service.request

import com.vintsarevich.secondlife.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface DoctorServiceApi {
    @POST("/doctor")
    fun checkUser(@Body data: User): Call<Int>
}