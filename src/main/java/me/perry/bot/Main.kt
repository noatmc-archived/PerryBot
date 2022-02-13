package me.perry.bot

import me.perry.bot.command.CommandManager
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import kotlin.system.measureTimeMillis

/**
 * @author perry.
 * @since 2/9/2022.
 * Starts up the Discord bot.
 */
fun main() {
    Main
}

object Main {
    val commandManager: CommandManager

    init {
        info("Loading...")
        val time = measureTimeMillis {
            commandManager = CommandManager()
            initJDA()
        }
        info("Loaded in $time ms!")
    }

    private fun initJDA() {
        JDABuilder.createDefault("Insert Discord BOT Token here.")
            .addEventListeners(commandManager)
            .disableCache(
                CacheFlag.ACTIVITY,
                CacheFlag.VOICE_STATE,
                CacheFlag.EMOTE,
                CacheFlag.CLIENT_STATUS,
                CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.ROLE_TAGS,
                CacheFlag.ONLINE_STATUS
            )
            .disableIntents(
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_WEBHOOKS,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.DIRECT_MESSAGE_TYPING
            )
            .setActivity(
                Activity.playing("+help")
            )
            .build()
            .awaitReady()
    }

    fun info(info: String) {
        println("[PerryBot/INFO]: $info")
    }

    fun error(error: String) {
        println("[PerryBot/ERROR]: $error")
    }
}

