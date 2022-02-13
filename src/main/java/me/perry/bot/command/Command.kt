package me.perry.bot.command

import me.perry.bot.Main
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.text.SimpleDateFormat
import java.util.*

abstract class Command(val name: String, val emoji: String, val description: String) {
    protected val time: String get() = SimpleDateFormat("h:mm:ss a").format(Date())

    fun execute(event: MessageReceivedEvent) {
        val user = event.message.author.asTag
        val server = event.message.guild.name
        react(if (onExecute(event)) "✅" else "❌", event.message)
        info("$user executed command in $server")
    }

    /**
     * Return true to react with a check, false for an x.
     * This will have to be revamped if other reactions are added,
     * maybe return a string for the name of the emoji?
     */
    protected abstract fun onExecute(event: MessageReceivedEvent): Boolean

    protected fun info(info: String) {
        Main.info("[$time]: $name: $info")
    }

    protected fun error(error: String) {
        Main.error("[$time]: $name: $error")
    }

    protected fun react(unicode: String, message: Message) {
        message.addReaction(unicode).queue()
    }
}