package com.mobile.week2_1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.week2_1.data.SongDto

class SharedViewModel : ViewModel() {
    private val _selectedAlbum = MutableLiveData<SongDto>()
    val selectedAlbum: LiveData<SongDto> get() = _selectedAlbum

    fun setAlbum(value: SongDto) {
        _selectedAlbum.value = value
    }
}