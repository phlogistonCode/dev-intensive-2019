package com.phlogiston.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage() : String

    companion object AbstractFactory {
        var cacheId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String = "text", payload: Any?, isIncoming: Boolean = false): BaseMessage {
            cacheId++

            return when(type) {
                "image" -> ImageMessage("$cacheId", from, chat, isIncoming, date, payload as String)
                else -> TextMessage("$cacheId", from, chat, isIncoming, date, payload as String)
            }
        }
    }
}