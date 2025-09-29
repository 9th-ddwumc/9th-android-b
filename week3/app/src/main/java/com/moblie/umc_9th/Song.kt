package com.moblie.umc_9th

data class Song(
    var title: String = "",
    var singer: String = "",
    var coverImg: Int = 0, // 앨범 커버 이미지 리소스 ID
    var playTime: Int = 0, // 총 재생 시간 (초 단위)
    var isPlaying: Boolean = false, // 현재 재생 중인지 여부
    var isRepeat: Boolean = false, // 한 곡 반복 여부 (또는 반복 모드 상태)
    var isRandom: Boolean = false, // 랜덤 재생 여부
    var music: String = "" // 실제 음악 파일 이름 (mp3 파일 이름 등)
)
