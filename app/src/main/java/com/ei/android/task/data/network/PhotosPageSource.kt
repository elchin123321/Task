package com.ei.android.task.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ei.android.task.data.network.model.PhotosResponseDTO
import com.ei.android.task.data.network.service.PhotosService
import com.ei.android.task.presentation.PhotoUi
import retrofit2.HttpException

class PhotosPageSource(
    private val service: PhotosService,
    private val mapper: PhotosResponseDTO.Mapper<PhotoUi>
) : PagingSource<Int, PhotoUi>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoUi>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoUi> {
        val page: Int = params.key ?: FIRST_PAGE_INDEX
        val pageSize: Int = params.loadSize
        val response = service.fetchPhoto(page, pageSize)
        return if (response.isSuccessful) {
            val result = checkNotNull(response.body()).map {
                it.map(mapper)
            }
            val nextKey = if (result.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(data = result, prevKey = prevKey, nextKey = nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }


}