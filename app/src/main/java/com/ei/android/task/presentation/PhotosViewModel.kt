package com.ei.android.task.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ei.android.task.data.PhotosRepository

class PhotosViewModel(
    private val repository: PhotosRepository
) : ViewModel() {


    private val photos = MutableLiveData<PagingData<PhotoUi>>()

    suspend fun fetchPhotos(): LiveData<PagingData<PhotoUi>> {
        val response = repository.fetchPhotos().cachedIn(viewModelScope)
        photos.value = response.value
        return response
    }

}