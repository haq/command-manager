package me.affanhaq.commandmanager;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public interface Command {

    /**
     * Called on command execute.
     *
     * @param args the arguments for the command
     * @return whether the command was successful or not
     */
    boolean handle(@NotNull MessageReceivedEvent event, @NotNull String[] args);

    /**
     * @return the usage of the command
     */
    String usage();

    /**
     * @return whether to delete the command after calling handle()
     */
    boolean delete();

}