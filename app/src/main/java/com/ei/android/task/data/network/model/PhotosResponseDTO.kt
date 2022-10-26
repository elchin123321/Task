package com.ei.android.task.data.network.model

import com.google.gson.annotations.SerializedName

data class PhotosResponseDTO(
    @SerializedName("id") private val id: String? = null,
    @SerializedName("description") private val description: String? = null,
    @SerializedName("user") private val user: UserDTO? = null,
    @SerializedName("urls") private val urls: PhotosURLsDTO? = null
) {
    interface Mapper<T> {
        fun map(id: String?, description: String?, user: UserDTO?, urls: PhotosURLsDTO?): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(id, description, user, urls)
}