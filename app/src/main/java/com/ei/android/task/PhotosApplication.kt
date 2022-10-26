package com.ei.android.task

import android.app.Application
import com.ei.android.task.data.PhotosRepository
import com.ei.android.task.data.network.model.ToDataMapper
import com.ei.android.task.data.network.service.PhotosService
import com.ei.android.task.data.network.service.RetroInstance
import com.ei.android.task.presentation.PhotosViewModel

class PhotosApplication : Application() {

    lateinit var viewModel: PhotosViewModel
    override fun onCreate() {
        super.onCreate()
        val service = RetroInstance.getRetroInstance().create(PhotosService::class.java)
        val photosRepository = PhotosRepository.Base(service, ToDataMapper())
        viewModel = PhotosViewModel(photosRepository)
    }
}