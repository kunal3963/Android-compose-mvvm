package com.example.testingsample

data class School(
    val dbn: String,
    val school_name: String,
    val phone_number: String,
    val website: String
)


data class SchoolInfo(
    val dbn: String,
    val school_name: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String
)
