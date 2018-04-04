package me.ihaq.commandmanager.commands;

import me.ihaq.commandmanager.Command;
import me.ihaq.commandmanager.CommandManager;

public class HelpCommand implements Command {

    @Override
    public boolean onCommand(String[] args) {

        System.out.println("Here is a list of all the possible commands:");
        for (Command command : CommandManager.INSTANCE.COMMANDS.values()) {
            System.out.println(command.usage());
        }

        return true;
    }

    @Override
    public String usage() {
        return "USAGE: [ help ]";
    }
}
