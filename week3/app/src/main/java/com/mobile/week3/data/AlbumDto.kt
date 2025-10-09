package com.mobile.week3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("AlbumTable")
data class AlbumDto(
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    var title: String? = "제목",
    var singer: String? = "가수",
    var coverImg: Int? = null
)