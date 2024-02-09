package com.noobnuby.plugin.jda

import com.noobnuby.plugin.Main
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

object JdaMain {
    fun Bot() {
        val jda = JDABuilder.createDefault(Main.instance.config["BOT_TOKEN"].toString())
            .addEventListeners(JdaEvent)
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.playing("${Main.instance.config["SERVER_NAME"]}서버 인증"))
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .build()
        jda.awaitReady()
    }
}