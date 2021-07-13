package com.example.pixelphotofinder.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pixelphotofinder.networkapi.PixelApi
import retrofit2.HttpException
import java.io.IOException

class PixelPagingSource(
    private val pixelApi: PixelApi,
    private val query: String
): PagingSource<Int,PhotoItem>(){
    override fun getRefreshKey(state: PagingState<Int, PhotoItem>): Int? {
        Log.d("PixelPhoto", "refresh: ")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        Log.d("PixelPhoto", "load: ")
        val pageNum = params.key ?: 1
        return try{
            val pixelResponse = pixelApi.searchPhotos(
                query,
                pageNum,
                params.loadSize
            )
            val photos = pixelResponse.photos
            //Log.d("PixelPhoto", "load: "+ photos[0].photographer)
            LoadResult.Page(
                photos,
                if(pageNum == 1) null else pageNum - 1,
                if(photos.isEmpty())  null else pageNum + 1
            )
        }catch (e: IOException){
            Log.d("PixelPhoto", "load: error "+e)
            LoadResult.Error(e)
        }catch (e: HttpException){
            Log.d("PixelPhoto", "load: error "+e)
            LoadResult.Error(e)
        }
    }
}