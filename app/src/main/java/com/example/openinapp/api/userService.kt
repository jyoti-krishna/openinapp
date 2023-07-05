package com.example.openinapp.api

import com.example.openinapp.models.userList
import retrofit2.Response
import retrofit2.http.GET

interface userService {
    @GET("v1/dashboardNew")
    suspend fun getUser():Response<userList>
}