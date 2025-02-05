package com.jmrsa.data.use_cases

import com.jmrsa.domain.models.Player
import com.jmrsa.domain.repositories.PlayerRepository
import com.jmrsa.domain.use_cases.LobbyUseCase
import javax.inject.Inject

class LobbyUseCaseImpl @Inject constructor(private val playerRepository: PlayerRepository) : LobbyUseCase {
    override suspend fun observeLobbyMessages() = playerRepository.observeSessionFlow()

    override suspend fun logPlayer(player: Player) {
        playerRepository.logNewPlayer(player)
    }
}