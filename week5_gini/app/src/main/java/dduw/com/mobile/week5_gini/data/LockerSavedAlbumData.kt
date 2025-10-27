package dduw.com.mobile.week5_gini.data

import dduw.com.mobile.week5_gini.R

data class LockerSavedAlbumData(
    val album: String = "", //앨범제목
    val singer: String? = "", //가수
    val albumInfo: String? = "", //앨범 정보명
    val image : Int = R.drawable.img_album_exp2
)
