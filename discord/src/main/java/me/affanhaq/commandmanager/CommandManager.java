package me.affanhaq.commandmanager;

import me.affanhaq.commandmanager.exception.CommandArgumentException;
import me.affanhaq.commandmanager.exception.CommandParseException;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String[], Command> commandMap;
    private final String prefix;

    /**
     * @param prefix the prefix you want to be used for the command manager
     */
    public CommandManager(@NotNull String prefix) {
        this.prefix = prefix;
        this.commandMap = new HashMap<>();
    }

    /**
     * @param name    the name of the command
     * @param command the command object that corresponds to the command
     */
    public void register(@NotNull String name, @NotNull Command command) {
        register(new String[]{name}, command);
    }

    /**
     * @param names   the name/alias of the command
     * @param command the command object that corresponds to the command
     */
    public void register(@NotNull String[] names, @NotNull Command command) {
        commandMap.put(names, command);
    }

    /**
     * @param command the command object to be searched for and removed
     */
    public void unregister(@NotNull Command command) {
        commandMap.entrySet().removeIf(entry -> entry.getValue() == command);
    }

    /**
     * @param rawMessage the raw message entered by the user
     * @throws CommandParseException    thrown if no command was found
     * @throws CommandArgumentException thrown if the command was run and its onCommand method returned false
     */
    public void parseCommand(@NotNull String rawMessage, @NotNull User user) throws CommandParseException, CommandArgumentException {

        if (!rawMessage.startsWith(prefix)) {
            throw new CommandParseException("Message does not start with prefix.");
        }

        boolean safe = rawMessage.split(prefix, 2).length > 1;

        if (safe) {

            String beheadedRawMessage = rawMessage.substring(1);

            String[] args = beheadedRawMessage.split(" ");
            String commandName = args[0];

            if (args.length == 1) { // no arguments are provided
                args = new String[0];
            } else {
                args = Arrays.copyOfRange(args, 1, args.length); // removing the command name
            }

            Command command = getCommand(commandName);
            if (command == null) {
                throw new CommandParseException("Command not found.");
            }

            if (!command.onCommand(user, args)) {
                throw new CommandArgumentException(command.usage());
            }

        } else {
            throw new CommandParseException("Command not found.");
        }
    }

    /**
     * Get the command by one of its aliases.
     *
     * @param name The name of the command you are looking for, should be one of the commands aliases.
     * @return returns the Command if found
     */
    @Nullable
    private Command getCommand(@NotNull String name) {
        return commandMap.entrySet().stream()
                .filter(commandEntry -> Arrays.stream(commandEntry.getKey()).anyMatch(s -> s.equalsIgnoreCase(name)))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }

    /**
     * @return returns the HashMap that contains all the commands
     */
    @NotNull
    public Map<String[], Command> getCommandMap() {
        return commandMap;
    }

    /**
     * @return returns the prefix required for all commands
     */
    @NotNull
    public String getPrefix() {
        return prefix;
    }

}