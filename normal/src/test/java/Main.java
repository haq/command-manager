import me.affanhaq.commandmanager.Command;
import me.affanhaq.commandmanager.CommandManager;
import me.affanhaq.commandmanager.exception.CommandArgumentException;
import me.affanhaq.commandmanager.exception.CommandParseException;

public class Main {
    public static void main(String[] args) {

        CommandManager commandManager = new CommandManager("-");

        commandManager.register(new String[]{"test", "t"}, new TestCommand());

        try {
            commandManager.parseCommand("-t"); // will call TestCommand
        } catch (CommandParseException e) {
            // handle parse exception
            e.printStackTrace();
        } catch (CommandArgumentException e) {
            // handle argument exception
            e.printStackTrace();
        }
    }

    private static class TestCommand implements Command {

        @Override
        public boolean onCommand(String[] args) {

            // process your command

            return false; // whether the command was successful or not
        }

        @Override
        public String usage() {
            return "test [arg1] [arg2]";
        }
    }
}
