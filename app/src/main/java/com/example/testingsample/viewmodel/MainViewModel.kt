package com.example.testingsample.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingsample.School
import com.example.testingsample.SchoolInfo
import com.example.testingsample.network.ApiService
import com.example.testingsample.repository.Repository
import kotlinx.coroutines.*

class MainViewModel(
    private val repository: Repository,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    var a by mutableStateOf(-1)
    var s by mutableStateOf(true)
    var launch: Job? = null
    var listOfSchoolInfo: List<SchoolInfo> by mutableStateOf(listOf())
    var listOfSchool: List<School> by mutableStateOf(listOf())

    fun getSchools() {
        viewModelScope.launch(defaultDispatcher) {
            try {
                delay(2000)
                //val allSchool = repository.getAllSchool()
                //listOfSchool = allSchool
                s = false
                a = 50
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }


    fun getSchoolInfo() {
        launch = CoroutineScope(defaultDispatcher).launch {
            try {
                listOfSchool = repository.getAllSchool()
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

}
class ViewModelTestClass(
    private val repository: Repository,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    var a by mutableStateOf(-1)

    fun getSchools() {
        viewModelScope.launch(defaultDispatcher) {
            try {
                delay(2000)
                a = 50
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}