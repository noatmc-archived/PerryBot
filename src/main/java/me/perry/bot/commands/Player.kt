package me.perry.bot.commands

import me.perry.bot.utils.JsonUtil.getUUIDFromName
import me.perry.bot.utils.JsonUtil.parse
import me.perry.bot.utils.JsonUtil.parseArray
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author perry.
 * @since 2/9/2022.
 */
class Player : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val args = listOf(*event.message.contentRaw.split(" ".toRegex()).toTypedArray())
        if (event.message.contentRaw.startsWith("+player", ignoreCase = true)) {
            println(SimpleDateFormat("h:mm:ss a ").format(Date()) + event.message.author.asTag + " Executed the player command.")
            if (args.size > 1) {
                event.message.addReaction("✅").queue()
                val uuid = getUUIDFromName(args[1])
                val embedBuilder = EmbedBuilder()
                embedBuilder.setAuthor(
                    args[1],
                    "https://namemc.com/profile/" + args[1],
                    "https://cravatar.eu/head/$uuid/128"
                )
                embedBuilder.setColor(
                    Color(
                        203,
                        8,
                        73,
                        255
                    )
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
                event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
            } else {
                event.message.addReaction("❌").queue()
            }
        }
    }
}