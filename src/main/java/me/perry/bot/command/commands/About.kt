package me.perry.bot.command.commands

import me.perry.bot.command.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

/**
 * @author perry.
 * @since 2/9/2022.
 */
class About : Command("About", ":grey_question:", "Tells you info about the bot.") {

    override fun onExecute(event: MessageReceivedEvent): Boolean {
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
            "Bot originally by noat#8700 & cleaned up/revamped by perry#1451.\n" +
                    "bush#8888 did some stuff too",
            false
        )
        embedBuilder.addField(
            ":page_facing_up: - Source",
            "Noat's original version is on GitHub. https://github.com/noatmc/2bot2australia\n Perry's version is on GitHub. https://github.com/notperry12345678902/PerryBot",
            false
        )
        event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        return true
    }
}