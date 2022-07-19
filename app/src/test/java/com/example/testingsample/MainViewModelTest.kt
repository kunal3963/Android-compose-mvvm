package com.example.testingsample

import com.example.testingsample.network.ApiService
import com.example.testingsample.repository.Repository
import com.example.testingsample.viewmodel.MainViewModel
import com.example.testingsample.viewmodel.ViewModelTest
import com.example.testingsample.viewmodel.ViewModelTestClass
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MainViewModelTest {

    @Mock
    lateinit var apiService: ApiService

    lateinit var repository: Repository

    lateinit var mainViewModel: MainViewModel
    lateinit var dispatcher: TestDispatcher

    @Before
    fun setup() {
        dispatcher = UnconfinedTestDispatcher()
        MockitoAnnotations.initMocks(this)
        repository = Repository(apiService)
        mainViewModel = MainViewModel(repository, dispatcher)
    }

    @Test
    fun `get all school test`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        //Dispatchers.setMain(testDispatcher)

        try {
            val viewModel = MainViewModel(repository, testDispatcher)
            viewModel.getSchools() // Uses testDispatcher, runs its coroutine eagerly
            advanceUntilIdle()
            assertEquals(50, viewModel.a)
        } catch (e:Exception){
            throw IllegalArgumentException()
        }
        finally {
            //Dispatchers.resetMain()
        }
        /*//Mockito.`when`(repository.getAllSchool())
        //  .thenReturn(listOf(School("dbnb", "movie", "", "new")))
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        val viewModel = MainViewModel(repository, testDispatcher)
        viewModel.getSchools()
        advanceUntilIdle()
        assertEquals(50, viewModel.a)
        //assertEquals(listOf(School("dbnb", "movie", "", "new")), mainViewModel.listOfSchool)*/
    }

    @Test
    fun `setting Main Dispatcher`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        //Dispatchers.setMain(testDispatcher)

        try {
            val viewModel = ViewModelTestClass(repository, testDispatcher)
            viewModel.getSchools() // Uses testDispatcher, runs its coroutine eagerly
            advanceUntilIdle()
            assertEquals(50, viewModel.a)
        } finally {
            //Dispatchers.resetMain()
        }
    }

    @Test
    fun `get all school size`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Mockito.`when`(repository.getAllSchool())
            .thenReturn(listOf(School("dbn", "movie", "", "news")))
        val viewModel = MainViewModel(repository, testDispatcher)
        viewModel.getSchools() // Uses testDispatcher, runs its coroutine eagerly
        advanceUntilIdle()
        assertEquals(true, mainViewModel.listOfSchool.size > 0)
    }
}