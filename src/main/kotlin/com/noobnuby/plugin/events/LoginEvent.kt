package com.noobnuby.plugin.events

import com.noobnuby.plugin.Main
import com.noobnuby.plugin.Main.Companion.playerAuth
import com.noobnuby.plugin.utils.PlayerAuthStr
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import java.io.File

class LoginEvent: Listener {
    @EventHandler
    fun PlayerLoginEvent(e:AsyncPlayerPreLoginEvent) {
        val uuid = e.uniqueId
        val file = File(Main.instance.dataFolder,"login.yml")
        val config = YamlConfiguration.loadConfiguration(file)
        if (config[uuid.toString()] == null) {
            if (playerAuth[uuid] == null) PlayerAuthStr.SetAuthStr(uuid)
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, MiniMessage.miniMessage().deserialize("<green>아래의 코드를 디코 봇 DM으로 보내주세요\n\n<yellow>------<aqua>${playerAuth.get(uuid)}<yellow>------"))
        }
    }
}