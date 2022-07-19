package com.example.testingsample.repository

import android.util.Log
import com.example.testingsample.School
import com.example.testingsample.SchoolInfo
import com.example.testingsample.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getAllSchool() : List<School>{
        return apiService.getAllSchool()
    }

    suspend fun getSchool() : List<SchoolInfo>{
        return apiService.getSchool()
    }
}