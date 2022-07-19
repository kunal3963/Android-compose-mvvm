package com.example.testingsample

import com.example.testingsample.network.ApiService
import com.example.testingsample.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var apiService = ApiService.getInstance()
    private val testDispatcher = UnconfinedTestDispatcher()
    //var viewModel = MainViewModel(apiService, testDispatcher)

    @Before
    fun setUp() {
        //Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun test_value() = runBlocking {
        //viewModel.getApiRequest()
        //assertEquals(false, viewModel.s)
    }

    @After
    fun tear_down() {
        //Dispatchers.resetMain()
    }
}