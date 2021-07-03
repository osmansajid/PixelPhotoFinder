package com.example.pixelphotofinder.networkapi

import com.example.pixelphotofinder.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PixelApi {
    companion object{
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val ACCESS_KEY = BuildConfig.PIXEL_ACCESS_KEY
    }


    @Headers("X-Ratelimit-Remaining", "Authorization: Client-ID $ACCESS_KEY")
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int
    ): PixelResponse
}
