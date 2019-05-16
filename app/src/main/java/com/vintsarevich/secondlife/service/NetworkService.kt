package com.vintsarevich.secondlife.service

import com.vintsarevich.secondlife.service.request.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkService private constructor() {
    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var mInstance: NetworkService? = null
        private const val BASE_URL = "http://192.168.0.106:8080"

        val instance: NetworkService
            get() {
                if (mInstance == null) {
                    mInstance = NetworkService()
                }
                return mInstance as NetworkService
            }
    }

    fun getDoctorServiceApi(): DoctorServiceApi {
        return mRetrofit.create(DoctorServiceApi::class.java)
    }

    fun getOrderServiceApi(): OrderServiceApi {
        return mRetrofit.create(OrderServiceApi::class.java)
    }

    fun getDiseaseServiceApi(): DiseaseServiceApi {
        return mRetrofit.create(DiseaseServiceApi::class.java)
    }

    fun getDiseaseStageServiceApi(): DiseaseStageServiceApi {
        return mRetrofit.create(DiseaseStageServiceApi::class.java)
    }

    fun getLabServiceApi(): LabServiceApi {
        return mRetrofit.create(LabServiceApi::class.java)
    }

    fun getTestRecommendationServiceApi(): TestRecommendationServiceApi {
        return mRetrofit.create(TestRecommendationServiceApi::class.java)
    }

    fun getTherapyApi(): TherapyServiceApi {
        return mRetrofit.create(TherapyServiceApi::class.java)
    }

    fun getFileApi(): FileServiceApi {
        return mRetrofit.create(FileServiceApi::class.java)
    }
}