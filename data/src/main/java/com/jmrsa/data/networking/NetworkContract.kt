package com.jmrsa.data.networking

import com.jmrsa.data.BuildConfig
import com.jmrsa.protocol.Protocol


object NetworkContract {
    const val BASE_SOCKET_URL = BuildConfig.BASE_SOCKET_URL
    const val LOBBY_SOCKET_URL = "$BASE_SOCKET_URL${Protocol.LOBBY_PATH}"
}
