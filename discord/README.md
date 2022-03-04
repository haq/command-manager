## Example

```java
import me.affanhaq.commandmanager.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Main {

    public static final CommandManager COMMAND_MANAGER = new CommandManager("-");

    public static void main(String[] args) {

        // registering the command
        COMMAND_MANAGER.register("test", new TestCommand());
        // or
        COMMAND_MANAGER.register(new String[]{"test", "tst", "t"}, new TestCommand());

        JDABuilder.createDefault("BOT TOKEN")
            .addEventListeners(new Main())
            .build();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            COMMAND_MANAGER.parseCommand(event);
        } catch (CommandParseException | CommandArgumentException e) {
            e.printStackTrace();
        }
    }

    private static class TestCommand implements Command {

        @Override
        public boolean handle(@NotNull MessageReceivedEvent event, @NotNull String[] args) {

            // process your command

            return false; // whether the command was successful or not
        }

        @Override
        public String usage() {
            return "test [arg1] [arg2]";
        }

        @Override
        public boolean delete() {
            return false;
        }
    }

}
```