package pawtropolis.game.command;

import lombok.Getter;
import pawtropolis.game.command.domain.Command;
import pawtropolis.game.command.domain.HelpCommand;
import pawtropolis.game.command.domain.WrongCommand;
import pawtropolis.game.command.domain.gamecommand.*;
import pawtropolis.game.command.domain.gamecommand.parameterizedcommand.DropCommand;
import pawtropolis.game.command.domain.gamecommand.parameterizedcommand.GetCommand;
import pawtropolis.game.command.domain.gamecommand.parameterizedcommand.GoCommand;
import pawtropolis.game.command.domain.systemcommand.ExitCommand;
import pawtropolis.game.command.domain.gamecommand.SaveCommand;
import pawtropolis.game.command.domain.systemcommand.StartCommand;

import java.util.Arrays;

public enum CommandTrigger {
    BAG("bag", BagCommand.class),
    DROP("drop", DropCommand.class),
    GET("get", GetCommand.class),
    GO("go", GoCommand.class),
    HELP("help", HelpCommand.class),
    LOOK("look", LookCommand.class),
    START("start", StartCommand.class),
    EXIT("exit", ExitCommand.class),
    SAVE("save", SaveCommand.class),
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
