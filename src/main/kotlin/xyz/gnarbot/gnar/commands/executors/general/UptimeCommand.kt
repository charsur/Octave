package xyz.gnarbot.gnar.commands.executors.general

import xyz.gnarbot.gnar.Constants
import xyz.gnarbot.gnar.commands.handlers.Command
import xyz.gnarbot.gnar.commands.handlers.CommandExecutor
import xyz.gnarbot.gnar.utils.Note

@Command(aliases = arrayOf("uptime"), description = "Show the getBot's uptime.")
class UptimeCommand : CommandExecutor() {
    override fun execute(note: Note, args: List<String>) {
        val s = bot.uptime / 1000
        val m = s / 60
        val h = m / 60
        val d = h / 24

        note.respond().embed("Bot Uptime") {
            color = Constants.COLOR
            description = "$d days, ${h % 24} hours, ${m % 60} minutes and ${s % 60} seconds"
        }.rest().queue()
    }
}
