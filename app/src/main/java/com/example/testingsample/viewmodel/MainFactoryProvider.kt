package com.example.testingsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testingsample.network.ApiService
import com.example.testingsample.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher

class MainFactoryProvider(private val repository: Repository, private val default: CoroutineDispatcher) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(this.repository, this.default) as T
        else
            throw IllegalArgumentException()
    }
}