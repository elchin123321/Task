package com.ei.android.task.data.network.model

import com.ei.android.task.presentation.PhotoUi

class ToDataMapper : PhotosResponseDTO.Mapper<PhotoUi> {
    override fun map(
        id: String?,
        description: String?,
        user: UserDTO?,
        urls: PhotosURLsDTO?
    ): PhotoUi {
        return PhotoUi(
            id ?: "",
            description ?: "No description",
            user?.name ?: "",
            urls?.small ?: "",
            urls?.full ?: ""
        )
    }
}