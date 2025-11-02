package dduw.com.mobile.week5_gini.data

import dduw.com.mobile.week5_gini.R

data class LockerSavedSongData(
    val song: String = "", //노래제목
    val singer: String? = "", //가수
    val albumImage: Int = R.drawable.img_album_exp2
)
