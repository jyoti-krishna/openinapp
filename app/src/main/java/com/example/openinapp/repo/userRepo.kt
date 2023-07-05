package com.example.openinapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openinapp.api.userService
import com.example.openinapp.models.userList




class userRepo(private val userService: userService) {
    private val userLiveData= MutableLiveData<userList>()
    public val user:LiveData<userList>
        get()= userLiveData
    suspend fun getUser(){
        val res=userService.getUser()
        if(res?.body() != null){
            userLiveData.postValue(res.body())
        }
    }
}