package com.ei.android.task.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ei.android.task.data.network.PhotosPageSource
import com.ei.android.task.data.network.model.PhotosResponseDTO
import com.ei.android.task.data.network.service.PhotosService
import com.ei.android.task.presentation.PhotoUi


interface PhotosRepository {
    suspend fun fetchPhotos(): LiveData<PagingData<PhotoUi>>
    class Base(
        private val service: PhotosService,
        private val mapper: PhotosResponseDTO.Mapper<PhotoUi>
    ) : PhotosRepository {
        override suspend fun fetchPhotos(): LiveData<PagingData<PhotoUi>> {
            return Pager(
                config = PagingConfig(pageSize = 8),
                pagingSourceFactory = {
                    PhotosPageSource(service, mapper)
                }).liveData


        }

    }
}
