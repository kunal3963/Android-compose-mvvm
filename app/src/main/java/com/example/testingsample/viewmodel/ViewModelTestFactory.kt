package com.example.testingsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher

class ViewModelTestFactory(private val default: CoroutineDispatcher) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ViewModelTest::class.java)){
            return ViewModelTest(default) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}