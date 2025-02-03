package com.jmrsa.incohearentgame.di

import com.jmrsa.data.networking.RealtimeClientImpl
import com.jmrsa.data.networking.api.RealtimeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.WebSockets

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideHttpClient() : HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(WebSockets)
        }
    }

    @Singleton
    @Provides
    fun provideRealtimeClient(httpClient: HttpClient) : RealtimeClient {
        return RealtimeClientImpl(httpClient)
    }
}