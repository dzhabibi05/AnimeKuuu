package com.pemmob.animefind.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.createSavedStateHandle
import com.pemmob.animefind.data.repository.AnimeRepository
import com.pemmob.animefind.ui.detail.DetailViewModel
import com.pemmob.animefind.ui.home.HomeViewModel

object ViewModelFactory {

    fun provideHomeViewModelFactory(repository: AnimeRepository): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(repository) as T
            }
        }
    }

    fun provideDetailViewModelFactory(repository: AnimeRepository): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val savedStateHandle = extras.createSavedStateHandle()
                return DetailViewModel(savedStateHandle, repository) as T
            }
        }
    }
}