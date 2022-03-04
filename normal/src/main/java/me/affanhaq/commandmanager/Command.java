package me.affanhaq.commandmanager;

import org.jetbrains.annotations.NotNull;

public interface Command {

    /**
     * Called on command execute.
     *
     * @param args the arguments for the command
     * @return whether the command was successful or not
     */
    boolean handle(@NotNull String[] args);

    /**
     * @return the usage of the command
     */
    String usage();

}