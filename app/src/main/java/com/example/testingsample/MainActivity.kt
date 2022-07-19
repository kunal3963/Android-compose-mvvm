package com.example.testingsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.testingsample.network.ApiService
import com.example.testingsample.repository.Repository
import com.example.testingsample.ui.theme.TestingSampleTheme
import com.example.testingsample.viewmodel.MainFactoryProvider
import com.example.testingsample.viewmodel.MainViewModel
import com.example.testingsample.viewmodel.ViewModelTestClass
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var repository: Repository
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    showSchoolList()
                }
            }
        }
    }

    @Composable
    fun showSchoolList() {
        apiService = ApiService.getInstance()
        repository = Repository(apiService)
        mainViewModel =
            ViewModelProvider(this, MainFactoryProvider(repository, Dispatchers.IO)).get(
                MainViewModel::class.java
            )
        mainViewModel.getSchools()
        SchoolList(mainViewModel.listOfSchool, mainViewModel.s)
    }

    @Composable
    fun SchoolList(list: List<School>, status: Boolean) {
        if (status) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.secondary,
                    strokeWidth = 7.dp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.d("Kunal", "size:" + list.size)
                itemsIndexed(list) { index, item ->
                    DisplaySchool(index, item)
                }
            }
        }
    }

    @Composable
    fun DisplaySchool(index: Int, item: School) {
        Card(
            backgroundColor = MaterialTheme.colors.secondary,
            shape = RoundedCornerShape(7.dp),
            elevation = 7.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clickable { }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.school_name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )
                Text(
                    text = item.phone_number,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )
                Text(
                    text = item.website,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )
            }
        }
    }
}

