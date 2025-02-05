package com.jmrsa.data.networking

import com.jmrsa.data.BuildConfig


object NetworkContract {
    const val BASE_SOCKET_URL = BuildConfig.BASE_SOCKET_URL
    const val LOBBY_SOCKET_URL = "$BASE_SOCKET_URL/lobby"
}