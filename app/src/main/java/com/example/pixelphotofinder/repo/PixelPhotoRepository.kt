package com.example.pixelphotofinder.repo

import com.example.pixelphotofinder.networkapi.PixelApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixelPhotoRepository @Inject constructor(private val pixelApi: PixelApi) {
}