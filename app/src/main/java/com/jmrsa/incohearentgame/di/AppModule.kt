package com.jmrsa.incohearentgame.di

import com.jmrsa.data.networking.api.realtime_client.RealtimeClientImpl
import com.jmrsa.data.networking.api.realtime_client.RealtimeClient
import com.jmrsa.data.repositories.PlayerRepositoryImpl
import com.jmrsa.data.use_cases.LobbyUseCaseImpl
import com.jmrsa.domain.repositories.PlayerRepository
import com.jmrsa.domain.use_cases.LobbyUseCase
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

    @Singleton
    @Provides
    fun providePlayerRepository(client: RealtimeClient): PlayerRepository {
        return PlayerRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideLobbyUseCase(impl: LobbyUseCaseImpl): LobbyUseCase = impl
}