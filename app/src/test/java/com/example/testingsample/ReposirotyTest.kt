package com.example.testingsample

import com.example.testingsample.network.ApiService
import com.example.testingsample.repository.Repository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ReposirotyTest {

    @Mock
    lateinit var apiService: ApiService

    lateinit var repository: Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repository = Repository(apiService)
    }

    @Test
    fun `get all movies test`() = runTest{
        Mockito.`when`(repository.getAllSchool()).thenReturn(listOf<School>())
        val allSchool = repository.getAllSchool()
        Assert.assertEquals(listOf<School>(), allSchool)
    }

    @Test
    fun `get movies test`() = runTest{
        Mockito.`when`(repository.getSchool()).thenReturn(listOf<SchoolInfo>())
        val allSchool = repository.getSchool()
        Assert.assertEquals(listOf<School>(), allSchool)
    }

}