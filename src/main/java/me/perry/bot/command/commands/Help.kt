package me.perry.bot.command.commands

import me.perry.bot.Main
import me.perry.bot.command.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

/**
 * @author perry.
 * @since 2/9/2022.
 */
class Help : Command("Help", ":question:", "Lists commands") {

    override fun onExecute(event: MessageReceivedEvent): Boolean {
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
        Main.commandManager.commands.forEach {
            embedBuilder.addField(
                "${it.emoji} - ${it.name}",
                it.description,
                false
            )
        }
        event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
        return true
    }
}