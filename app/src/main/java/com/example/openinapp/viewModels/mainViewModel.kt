package com.example.openinapp.viewModels

import android.text.Editable
import androidx.lifecycle.*
import com.example.openinapp.api.retrofitHelper
import com.example.openinapp.api.userService
import com.example.openinapp.models.userList
import com.example.openinapp.repo.userRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mainViewModel(
    private val repo: userRepo
        ):ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getUser()
        }
    }
    val user:LiveData<userList>
    get() =repo.user

}