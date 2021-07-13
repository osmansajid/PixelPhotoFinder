package com.example.pixelphotofinder.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.pixelphotofinder.data.PixelPagingSource
import com.example.pixelphotofinder.networkapi.PixelApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixelPhotoRepository @Inject constructor(private val pixelApi: PixelApi) {
    fun searchPhotos(query: String) = Pager(
            config = PagingConfig(
                pageSize = 15,
                maxSize = 80,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {PixelPagingSource(pixelApi,query)}
        ).liveData
}