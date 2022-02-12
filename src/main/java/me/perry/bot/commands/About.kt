package me.perry.bot.commands

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
class About : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.contentRaw.equals("+about", ignoreCase = true)) {
            event.message.addReaction("✅").queue()
            println(SimpleDateFormat("h:mm:ss a ").format(Date()) + event.message.author.asTag + " Executed the about command.")
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("About Page")
            embedBuilder.setColor(
                Color(
                    119,
                    13,
                    189,
                    255
                )
            )
            embedBuilder.addField(
                ":technologist: - Authors",
                "Bot originally by noat#8700 & cleaned up/revamped by perry#1451.",
                false
            )
            embedBuilder.addField(
                ":page_facing_up: - Source",
                "Noat's original version is on GitHub. https://github.com/noatmc/2bot2australia\n Perry's version is on GitHub. https://github.com/notperry12345678902/PerryBot",
                false
            )
            event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        }
    }
}