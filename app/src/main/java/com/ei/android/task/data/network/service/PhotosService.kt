package com.ei.android.task.data.network.service

import androidx.annotation.IntRange
import com.ei.android.task.data.network.model.PhotosResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {

    @GET("/photos")
    suspend fun fetchPhoto(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 2,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query(CLIENT_ID) id: String = API_KEY
    ): Response<List<PhotosResponseDTO>>

    companion object {
        private const val CLIENT_ID = "client_id"
        private const val API_KEY =
            "896d4f52c589547b2134bd75ed48742db637fa51810b49b607e37e46ab2c0043"
        private const val MAX_PAGE_SIZE = 30
        const val DEFAULT_PAGE_SIZE = 12
    }
}