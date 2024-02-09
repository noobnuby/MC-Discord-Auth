package com.noobnuby.plugin

import com.noobnuby.plugin.events.LoginEvent
import com.noobnuby.plugin.jda.JdaMain
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class Main : JavaPlugin() {
    companion object {
        lateinit var instance: Main
        var playerAuth: MutableMap<UUID,String> = mutableMapOf()
    }

    override fun onEnable() {
        instance = this

        saveDefaultConfig()
        saveResource("login.yml", false)

        logger.info("Enable Plugin!")

        server.pluginManager.apply {
            registerEvents(LoginEvent(),this@Main)
        }
        JdaMain.Bot()
        reloadConfig()
    }
}