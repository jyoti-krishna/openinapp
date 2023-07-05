package com.example.openinapp.viewModels

import android.view.View
import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.openinapp.repo.userRepo

class vmFactory(private val repo:userRepo):ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return mainViewModel(repo) as T
    }
}