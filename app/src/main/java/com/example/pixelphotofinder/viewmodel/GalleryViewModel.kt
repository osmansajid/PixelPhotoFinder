package com.example.pixelphotofinder.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pixelphotofinder.repo.PixelPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: PixelPhotoRepository): ViewModel() {
}