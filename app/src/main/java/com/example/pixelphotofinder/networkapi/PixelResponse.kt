package com.example.pixelphotofinder.networkapi

import com.example.pixelphotofinder.data.PhotoItem

data class PixelResponse(
    val photos: List<PhotoItem>
)