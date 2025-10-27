package ddwu.mobile.umc.flo_danu.data

import java.io.Serializable
import java.util.ArrayList

data class Album (
    val title: String = "",
    val singer: String = "",
    val coverImg: Int? = null,
    var songs: ArrayList<Song>? = null
): Serializable