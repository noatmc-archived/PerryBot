package me.perry.bot.command

import me.perry.bot.command.commands.*
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandManager : ListenerAdapter() {
    val commands = mutableListOf<Command>()
    var prefix = "+" // idk how to do saving and stuff u can do that later

    init {
        commands.addAll(
            listOf(
                About(),
                Dice(),
                Help(),
                Info(),
                Player(),
                Prefix()
            )
        )
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        commands.filter { event.message.contentRaw.startsWith(prefix + it.name, true) }.forEach {
            it.execute(event)
        }
    }
}