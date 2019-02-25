[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)

# command-manager
A simple command manager for Java application.

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

## Download
```xml
<repository>
   <id>maven-public</id>
   <url>http://nexus.ihaq.me/repository/maven-public/</url>
</repository>
```
```xml
<dependency>
    <groupId>me.ihaq</groupId>
    <artifactId>command-manager</artifactId>
    <version>1.0</version>
</dependency>
```