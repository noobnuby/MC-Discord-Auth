package com.noobnuby.plugin.jda

import com.noobnuby.plugin.Main
import com.noobnuby.plugin.Main.Companion.playerAuth
import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object JdaEvent : EventListener {
    override fun onEvent(e: GenericEvent) {
        if (e is ReadyEvent) {
            Main.instance.logger.info("Bot is ready!")
        }
        if(e is MessageReceivedEvent) {
            if (e.isFromType(ChannelType.PRIVATE)) {
                System.out.printf("[PM] %s: %s\n", e.getAuthor().getName(), e.getMessage().getContentDisplay())
                val matchingKey = playerAuth.entries.find { it.value == e.message.contentDisplay }?.key

                if (matchingKey != null) {
                    System.out.printf("[PM] %s: %s\n", e.getAuthor().getName(), e.getMessage().getContentDisplay())
                    File(Main.instance.dataFolder, "login.yml").apply {
                        val config = YamlConfiguration.loadConfiguration(this)
                        config.set(matchingKey.toString(), e.author.name)
                        config.save(this)
                    }
                    val playerName = Bukkit.getOfflinePlayer(matchingKey).name
                    e.channel.sendMessage("${playerName}계정으로 인증되었습니다!").queue()
                }
            }
        }
    }
}
