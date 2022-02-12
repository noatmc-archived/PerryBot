package me.perry.bot.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author perry.
 * @since 2/11/2022.
 */
class Dice : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.contentRaw.equals("+dice", ignoreCase = true)) {
            event.message.addReaction("✅").queue()
            println(SimpleDateFormat("h:mm:ss a ").format(Date()) + event.message.author.asTag + " executed the dice command in: " + event.message.guild.name)
            val embedBuilder = EmbedBuilder()
            embedBuilder.setTitle(":game_die: - Rolled " + kotlin.random.Random.nextInt(1, 10))
            embedBuilder.setColor(
                Color(
                    6,
                    188,
                    194,
                    255
                )
            )
            event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        }
    }
}