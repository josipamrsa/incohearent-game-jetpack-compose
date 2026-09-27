package com.example.features.lobby.domain.repository

interface LobbyRepository {
    suspend fun logNewPlayer()
}

class LobbyRepositoryImpl : LobbyRepository {
    override suspend fun logNewPlayer() {
        TODO("Not yet implemented")
    }
}