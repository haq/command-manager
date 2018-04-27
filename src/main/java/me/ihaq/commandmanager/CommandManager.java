package me.ihaq.commandmanager;

import me.ihaq.commandmanager.exception.CommandArgumentException;
import me.ihaq.commandmanager.exception.CommandParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String[], Command> commandMap = new HashMap<>();
    private String prefix;

    /**
     * @param prefix the prefix you wan't to be used for the command manager
     */
    public CommandManager(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @param name    the name of the command
     * @param command the command object that corresponds to the command
     */
    public void register(String name, Command command) {
        register(new String[]{name}, command);
    }

    /**
     * @param names   the name/alias of the command
     * @param command the command object that corresponds to the command
     */
    public void register(String[] names, Command command) {
        commandMap.put(names, command);
    }

    /**
     * @param command the command object to be searched for and removed
     */
    public void unregister(Command command) {
        commandMap.entrySet().removeIf(entry -> entry.getValue() == command);
    }

    /**
     * @param rawMessage the raw message entered by the user
     * @throws CommandParseException    thrown if no command was found
     * @throws CommandArgumentException thrown if the command was run and its onCommand method returned false
     */
    public void parseCommand(String rawMessage) throws CommandParseException, CommandArgumentException {

        if (!rawMessage.startsWith(prefix))
            return;

        boolean safe = rawMessage.split(prefix, 2).length > 1;

        if (safe) {

            String beheadedRawMessage = rawMessage.substring(1);

            String[] args = beheadedRawMessage.split(" ");
            String commandName = args[0];

            if (args.length == 1) // no arguments are provided
                args = new String[]{""};
            else
                args = Arrays.copyOfRange(args, 1, args.length); // removing the command name

            Command command = getCommand(commandName);

            if (command != null) {
                if (!command.onCommand(args)) {
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
        return commandMap.entrySet().stream()
                .filter(commandEntry -> Arrays.stream(commandEntry.getKey()).anyMatch(s -> s.equalsIgnoreCase(name)))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }

}