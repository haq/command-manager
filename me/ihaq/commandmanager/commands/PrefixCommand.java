package me.ihaq.commandmanager.commands;


import me.ihaq.commandmanager.Command;
import me.ihaq.commandmanager.CommandManager;

public class PrefixCommand implements Command {

    @Override
    public boolean onCommand(String[] args) {

        if (args.length >= 2) {
            CommandManager.INSTANCE.PREFIX = args[1];
            System.out.println("The command prefix has been changed to '" + args[1] + "'.");
            return true;
        }

        return false;

    }

    @Override
    public String usage() {
        return "USAGE: [ prefix <new_prefix> ]";
    }
}
