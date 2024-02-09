package com.noobnuby.plugin.utils

import com.noobnuby.plugin.Main.Companion.playerAuth
import org.bukkit.entity.Player
import java.util.UUID

object PlayerAuthStr {
    fun GetRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun SetAuthStr(uuid: UUID) {
        val randomString = GetRandomString(7)
        playerAuth[uuid] = randomString
    }
}