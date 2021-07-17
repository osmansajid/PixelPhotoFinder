package com.example.pixelphotofinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.pixelphotofinder.repo.PixelPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class GalleryViewModel @ViewModelInject constructor(private val repository: PixelPhotoRepository,
@androidx.hilt.Assisted savedState: SavedStateHandle): ViewModel() {

    companion object{
        const val LAST_QUERY_STRING = "last_query"
        const val CURRENT_QUERY_STRING = "colorful"
    }
    private val query = savedState.getLiveData(LAST_QUERY_STRING, CURRENT_QUERY_STRING)
    val photos = query.switchMap { qString ->
        repository.searchPhotos(qString)
    }.cachedIn(viewModelScope)

    fun searchPhotos(mQuery: String){
        query.value = mQuery
    }
}