package me.ihaq.commandmanager;

import me.ihaq.commandmanager.exception.CommandArgumentException;
import me.ihaq.commandmanager.exception.CommandParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CommandManager {
    INSTANCE;

    private String prefix = "!";
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

    public void parseCommand(String rawMessage) throws CommandParseException, CommandArgumentException {

        if (!rawMessage.startsWith(prefix))
            return;

        boolean safe = rawMessage.split(prefix, 2).length > 1;

        if (safe) {

            String beheadedRawMessage = rawMessage.substring(1);

            String[] args = beheadedRawMessage.split(" ");
            String label = args[0];

            if (args.length == 1)
                args = new String[]{""};
            else
                args = Arrays.copyOfRange(args, 1, args.length);

            Command command = getCommand(label);

            if (command != null) {
                if (!command.onCommand(label, args)) {
                    throw new CommandArgumentException(command.usage());
                }
            } else {
                throw new CommandParseException("Command not found.");
            }

        } else {
            throw new CommandParseException("Command not found.");
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

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
