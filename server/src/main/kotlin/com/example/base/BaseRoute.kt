package com.example.base

import com.jmrsa.protocol.ClientMessage
import com.jmrsa.protocol.decodeClientMessage
import io.ktor.server.routing.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ReceiveChannel

suspend fun Route.listenForIncomingFrames(incoming: ReceiveChannel<Frame>, action: (ClientMessage) -> Unit) {
    for (frame in incoming) {
        println("RECEIVED >> $frame")
        when (frame) {
            is Frame.Text -> {
                val receivedMessage = frame.readText()
                withErrorHandling {
                    val decodedMessage = decodeClientMessage(receivedMessage)
                    action(decodedMessage)
                }
            }

            else -> null
        }
    }
}

private fun withErrorHandling(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        println("There's been an error: >> " + e.message)
        println(e.stackTrace)
    }
}
