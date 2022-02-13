package me.perry.bot.command.commands

import me.perry.bot.Main
import me.perry.bot.command.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color

class Prefix : Command("Prefix", ":abcd:", "Change the command prefix") {

    override fun onExecute(event: MessageReceivedEvent): Boolean {
        val args = listOf(*event.message.contentRaw.split(" ".toRegex()).toTypedArray())
        val embedBuilder = EmbedBuilder()
        embedBuilder.setTitle("Command Prefix")
        embedBuilder.setColor(
            Color(
                50,
                240,
                200,
                255
            )
        )
        return when (args.size) {
            1 -> {
                embedBuilder.addField(
                    "Prefix",
                    "The current command prefix is ${Main.commandManager.prefix}",
                    false
                )
                event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
                true
            }
            2 -> {
                val prefix = args[1]
                Main.commandManager.prefix = prefix
                embedBuilder.addField(
                    "Prefix",
                    "Successfully changed the command prefix to $prefix",
                    false
                )
                event.channel.sendMessageEmbeds(embedBuilder.build()).queue()
                true
            }
            else -> false
        }
    }
}