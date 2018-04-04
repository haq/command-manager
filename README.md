# CommandManager
A simple command manager for Java.

## Creating a Command

```java
    public class TestCommand implements Command {
        @Override
        public boolean onCommand(String label, String[] args) {

            // handle your command

            return false; // whether the command was successful or not
        }

        @Override
        public String usage() {
            return "test [arg1] [arg2]";
        }
    }
```

## Registering a Command
```java
public class Main {

    public static void main(String[] args) {

        TestCommand test = new TestCommand();

        // registering
        CommandManager.INSTANCE.register("test", test);
        // or
        CommandManager.INSTANCE.register(new String[]{"test", "tst", "t"}, test);


        // un-registering
        CommandManager.INSTANCE.unregister(test);
    }
}
```

## Calling a Command
```java
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // every command must start with the prefix defined in CommandManager
        try {
            CommandManager.INSTANCE.parseCommand(scan.nextLine());
        } catch (CommandParseException | CommandArgumentException e) {
            e.printStackTrace();
        }
    }
}
```

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details