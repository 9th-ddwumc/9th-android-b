package dduw.com.mobile.week5_gini.data

data class Song(
    val title: String,
    val singer: String,
    val musicFileResId: Int = 0 // R.raw.어쩌구 같은 음악 파일 ID
)