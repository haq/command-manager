[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)
[![jitpack](https://jitpack.io/v/haq/command-manager.svg)](https://jitpack.io/#haq/command-manager)

# command-manager
A simple command manager for your Java application.

## Creating
```java
private class TestCommand implements Command {

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
```

## Registering
```java
public class Main {

    public static void main(String[] args) {

        CommandManager commandManager = new CommandManager("-");
        TestCommand test = new TestCommand();

        // registering
        commandManager.register("test", test);
        // or
        commandManager.register(new String[]{"test", "tst", "t"}, test);

        // un-registering
        commandManager.unregister(test);
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
```

## Calling
```java
public class Main {

    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager("-");

        commandManager.register(new String[]{"test", "tst", "t"}, new TestCommand());

        // every command must start with the prefix passed in the constructor of CommandManager
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
```
