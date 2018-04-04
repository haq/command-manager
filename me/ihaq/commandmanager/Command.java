package me.ihaq.commandmanager;

public interface Command {
    /**
     * Called on command execute.
     *
     * @param label the command name
     * @param args  the arguments for the command
     * @return Whether the command was successful or not
     */
    boolean onCommand(String label, String[] args);

    /**
     * @return The usage of the command
     */
    String usage();

}