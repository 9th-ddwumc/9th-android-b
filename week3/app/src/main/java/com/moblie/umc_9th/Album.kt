package com.moblie.umc_9th

import java.io.Serializable

data class Album(var title: String = "",
                 var singer: String = "",
                 var coverImg: Int = 0 // 앨범 커버 이미지 리소스 ID
) : Serializable
