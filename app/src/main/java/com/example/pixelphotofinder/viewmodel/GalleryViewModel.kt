package com.example.pixelphotofinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pixelphotofinder.repo.PixelPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class GalleryViewModel @ViewModelInject constructor(private val repository: PixelPhotoRepository): ViewModel() {

    companion object{
        const val CURRENT_QUERY_STRING = "colorful"
    }
    val query = MutableLiveData<String>(CURRENT_QUERY_STRING)
    val photos = query.switchMap { qString ->
        repository.searchPhotos(qString)
    }.cachedIn(viewModelScope)

    fun searchPhotos(mQuery: String){
        query.value = mQuery
    }
}