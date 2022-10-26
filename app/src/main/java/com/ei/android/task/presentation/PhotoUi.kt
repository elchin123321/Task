package com.ei.android.task.presentation

import com.ei.android.task.presentation.adapter.PhotosAdapter

data class PhotoUi(
    private val id: String,
    private val description: String,
    private val user: String,
    private val urlSmall: String,
    private val urlFull: String
) {
    fun sameItem(photoUi: PhotoUi): Boolean {
        return id == photoUi.id
    }

    interface Mapper<T> {
        fun map(description: String?, user: String, url: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(description, user, urlSmall)
    fun openPhoto(listener: PhotosAdapter.OnImageClickListener) = listener.onClickListener(urlFull)
}