package me.ihaq.commandmanager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CommandManager {
    INSTANCE;

    public String PREFIX = "!";
    public final Map<String[], Command> COMMANDS = new HashMap<>();

    public void register(String name, Command command) {
        register(new String[]{name}, command);
    }

    public void register(String[] names, Command command) {
        COMMANDS.put(names, command);
    }

    public void unregister(Command command) {
        COMMANDS.entrySet().removeIf(entry -> entry.getValue() == command);
    }

    /**
     * Tries to call a command depending on what the user entered.
     *
     * @param rawMessage The message the user entered.
     */
    public void processCommand(String rawMessage) {
        boolean safe = rawMessage.split(PREFIX, 2).length > 1;

        if (safe) {

            String beheadedRawMessage = rawMessage.substring(1);
            String[] args = beheadedRawMessage.split(" ");
            Command command = getCommand(args[0]);

            if (command != null) {
                if (!command.onCommand(args)) {
                    System.out.println(command.usage());
                }
            } else {
                System.out.println("Command not found, please try '" + PREFIX + "help'.");
            }

        } else {
            System.out.println("Command not found, please try '" + PREFIX + "help'.");
        }
    }

    /**
     * Get the command by one of its aliases.
     *
     * @param name The name of the command you are looking for, should be one of the commands aliases.
     * @return Returns the Command if found
     */
    private Command getCommand(String name) {
        return COMMANDS.entrySet().stream()
                .filter(commandEntry -> contains(commandEntry.getKey(), name))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }

    /**
     * @param list The list you want to search the text for
     * @param text The text you are searching for
     * @return Returns whether or not the list contains the text
     */
    private boolean contains(String[] list, String text) {
        return Arrays.stream(list)
                .anyMatch(s -> s.equalsIgnoreCase(text));
    }


}
