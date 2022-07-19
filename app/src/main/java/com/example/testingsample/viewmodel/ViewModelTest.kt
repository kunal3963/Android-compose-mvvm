package com.example.testingsample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelTest(private val default: CoroutineDispatcher) : ViewModel() {
    var test by mutableStateOf(0)

    fun getApi(){
        viewModelScope.launch((default)) {
            delay(5000)
            test = 50
        }
    }
}