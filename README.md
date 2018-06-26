[![license](https://img.shields.io/github/license/mashape/apistatus.svg) ](LICENSE)

# CommandManager
A simple command manager for Java application.

## Creating a Command

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

## Registering a Command
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

## Calling a Command
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

#### Maven
```xml
<repository>
    <id>ihaq-maven</id>
    <url>http://maven.ihaq.me/artifactory/libs-maven/</url>
</repository>

<dependency>
    <groupId>me.ihaq</groupId>
    <artifactId>command-manager</artifactId>
    <version>1.0.2</version>
</dependency>
```

#### Gradle
```gradle
repositories {
    maven {
        url "http://maven.ihaq.me/artifactory/libs-maven/"
    }
}

dependencies {
    compile 'me.ihaq:command-manager:1.0.2'
}
```