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
class Help : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.contentRaw.equals("+help", ignoreCase = true)) {
            event.message.addReaction("✅").queue()
            println(SimpleDateFormat("h:mm:ss a ").format(Date()) + event.message.author.asTag + " executed the help command in: " + event.message.guild.name)
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle("Help Page")
            embedBuilder.setColor(
                Color(
                    6,
                    188,
                    194,
                    255
                )
            )
            embedBuilder.addField(
                ":information_source: - Info",
                "Tells you info about 2b au.",
                false
            )
            embedBuilder.addField(
                ":grey_question: - About",
                "Tells you info about the bot.",
                false
            )
            embedBuilder.addField(
                ":computer: - Player",
                "Tells you info about a player playing on 2b au.",
                false
            )
            embedBuilder.addField(
                ":game_die: - Dice",
                "Rolls a dice.",
                false
            )
            event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        }
    }
}