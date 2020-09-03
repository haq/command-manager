package me.affanhaq.commandmanager;

import org.jetbrains.annotations.NotNull;

public interface Command {

    /**
     * Called on command execute.
     *
     * @param args the arguments for the command
     * @return Whether the command was successful or not
     */
    boolean onCommand(@NotNull String[] args);

    /**
     * @return The usage of the command
     */
    String usage();

}