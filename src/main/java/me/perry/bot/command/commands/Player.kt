package me.perry.bot.command.commands

import me.perry.bot.command.Command
import me.perry.bot.utils.JsonUtil.getUUIDFromName
import me.perry.bot.utils.JsonUtil.parse
import me.perry.bot.utils.JsonUtil.parseArray
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

/**
 * @author perry.
 * @since 2/9/2022.
 */
class Player : Command("Player", ":computer:", "Tells you info about a player playing on 2b au.") {

    override fun onExecute(event: MessageReceivedEvent): Boolean {
        val args = listOf(*event.message.contentRaw.split(" ".toRegex()).toTypedArray())
        if (args.size > 1) {
            val embedBuilder = EmbedBuilder()
            embedBuilder.setColor(
                Color(
                    203,
                    8,
                    73,
                    255
                )
            )
            try {
                val uuid = getUUIDFromName(args[1])
                embedBuilder.setAuthor(
                    args[1],
                    "https://namemc.com/profile/" + args[1],
                    "https://cravatar.eu/head/$uuid/128"
                )
                embedBuilder.addField(
                    "Join Date",
                    parse("https://api.2b2t.com.au/v1/players/$uuid", "joinDate"),
                    false
                )
                embedBuilder.addField(
                    "Play Time",
                    parse("https://api.2b2t.com.au/v1/players/$uuid", "playTime"),
                    false
                )
                embedBuilder.addField(
                    "Death(s)",
                    parseArray("https://api.2b2t.com.au/v1/players/$uuid", "statistics", "deaths"),
                    false
                )
            } catch (exception: Exception) {
                embedBuilder.addField(
                    "Error",
                    "Could not find player ${args[1]}, possibly offline?",
                    false
                )
                // this could be cleaned up
                event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
                return false
            }
            event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
            return true
        } else return false
    }
}