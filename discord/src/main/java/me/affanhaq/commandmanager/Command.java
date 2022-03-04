package me.affanhaq.commandmanager;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public interface Command {

    /**
     * Called on command execute.
     *
     * @param args the arguments for the command
     * @return Whether the command was successful or not
     */
    boolean onCommand(@NotNull MessageReceivedEvent event, @NotNull String[] args);

    /**
     * @return The usage of the command
     */
    String usage();

}