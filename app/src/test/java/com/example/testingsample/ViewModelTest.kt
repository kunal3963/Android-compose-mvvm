package com.example.testingsample

import com.example.testingsample.viewmodel.ViewModelTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewModelTestFile {
    var testDispatcher = UnconfinedTestDispatcher()
    var viewModel = ViewModelTest(testDispatcher)
    var test = 40
    suspend fun fetchData(): String {
        delay(5000L)
        test = 50
        return "Hello world"
    }

    @Test
    fun dataShouldBeHelloWorld() = runTest {
        val data = fetchData()
        assertEquals(50, test)
    }

    @Test
    fun settingMainDispatcher() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        //Dispatchers.setMain(testDispatcher)

        try {
            val viewModel = ViewModelTest(testDispatcher)
            viewModel.getApi() // Uses testDispatcher, runs its coroutine eagerly
            advanceUntilIdle()
            assertEquals(50, viewModel.test)
        } finally {
            //Dispatchers.resetMain()
        }
    }

}