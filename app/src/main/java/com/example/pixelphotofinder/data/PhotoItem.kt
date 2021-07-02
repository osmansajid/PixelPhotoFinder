package com.example.pixelphotofinder.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@kotlinx.parcelize.Parcelize
data class PhotoItem(
    val id: Int,
    val photographer: String,
    val src: PhotoUrls,
    val liked: Boolean
): Parcelable {

    @Parcelize
    data class  PhotoUrls(
        val original: String,
        val large: String,
        val medium: String,
        val small: String,
        val tiny: String
    ): Parcelable
}