package me.affanhaq.commandmanager;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

public interface Command {

    /**
     * Called on command execute.
     *
     * @param args the arguments for the command
     * @return Whether the command was successful or not
     */
    boolean onCommand(@NotNull User user, @NotNull String[] args);

    /**
     * @return The usage of the command
     */
    String usage();

}