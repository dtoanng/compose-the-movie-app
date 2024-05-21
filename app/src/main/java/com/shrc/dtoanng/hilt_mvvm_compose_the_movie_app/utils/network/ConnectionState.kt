package com.shrc.dtoanng.hilt_mvvm_compose_the_movie_app.utils.network

sealed class ConnectionState {
    data object Available : ConnectionState()
    data object Unavailable: ConnectionState()
}