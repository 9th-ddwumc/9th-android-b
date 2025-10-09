package com.mobile.week3.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.week3.data.AlbumDto

class SharedViewModel : ViewModel() {
    private val _selectedAlbum = MutableLiveData<AlbumDto>()
    val selectedAlbum: LiveData<AlbumDto> get() = _selectedAlbum

    fun setAlbum(value: AlbumDto) {
        _selectedAlbum.value = value
    }
}