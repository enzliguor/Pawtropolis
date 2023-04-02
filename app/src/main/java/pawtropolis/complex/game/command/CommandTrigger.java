package pawtropolis.complex.game.command;

import lombok.Getter;
import pawtropolis.complex.game.command.domain.*;

import java.util.Arrays;

public enum CommandTrigger {
    BAG("bag", BagCommand.class),
    DROP("drop", DropCommand.class),
    EXIT("exit", ExitCommand.class),
    GET("get", GetCommand.class),
    GO("go", GoCommand.class),
    HELP("help", HelpCommand.class),
    LOOK("look", LookCommand.class),
    WRONG_COMMAND("", WrongCommand.class);

    @Getter
    private final String trigger;

    @Getter
    private final Class<? extends Command> commandClass;

    CommandTrigger(String trigger, Class<? extends Command> commandClass){
        this.trigger = trigger;
        this.commandClass = commandClass;
    }

    public static CommandTrigger of(String trigger){
        return Arrays.stream(CommandTrigger.values())
                .filter(commandTrigger -> commandTrigger.getTrigger().equalsIgnoreCase(trigger))
                .findFirst()
                .orElse(WRONG_COMMAND);
    }


}
