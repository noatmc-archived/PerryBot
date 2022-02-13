package me.perry.bot.command.commands

import me.perry.bot.command.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

/**
 * @author perry.
 * @since 2/11/2022.
 */
class Dice : Command("Dice", ":game_die:", "Rolls a dice") {

    override fun onExecute(event: MessageReceivedEvent): Boolean {
        val embedBuilder = EmbedBuilder()
        embedBuilder.setTitle("$emoji - Rolled " + kotlin.random.Random.nextInt(1, 10))
        embedBuilder.setColor(
            Color(
                6,
                188,
                194,
                255
            )
        )
        event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        return true
    }
}