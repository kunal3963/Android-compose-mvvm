package com.example.testingsample.network

import com.example.testingsample.School
import com.example.testingsample.SchoolInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("s3k6-pzi2.json")
    suspend fun getAllSchool(): List<School>

    @GET("f9bf-2cp4.json")
    suspend fun getSchool(): List<SchoolInfo>

    companion object {
        fun getInstance(): ApiService {
            var apiService: ApiService? = null
            if (apiService == null) {
                apiService = Retrofit.Builder().baseUrl("https://data.cityofnewyork.us/resource/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}