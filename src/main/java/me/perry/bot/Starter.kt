package me.perry.bot

import me.perry.bot.commands.*
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag

/**
 * @author perry.
 * @since 2/9/2022.
 * Starts up the Discord bot.
 */
fun main() {
    JDABuilder.createDefault("Insert Discord BOT Token here.")
        .addEventListeners(
            About(),
            Help(),
            Info(),
            Player(),
            Dice()
        )
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