package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.data.local.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)