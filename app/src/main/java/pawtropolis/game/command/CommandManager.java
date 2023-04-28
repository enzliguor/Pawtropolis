package pawtropolis.game.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.game.command.domain.Command;
import pawtropolis.game.command.domain.ParameterizedCommand;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@Slf4j
@Component
public class CommandManager {

    private final ApplicationContext applicationContext;

    private Map<CommandTrigger, Command> commands;

    @Autowired
    private CommandManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private void init() {
        this.commands = new EnumMap<>(CommandTrigger.class);
        Arrays.stream(CommandTrigger.values())
                .forEach(commandTrigger -> commands.put(
                        commandTrigger,
                        applicationContext.getBean(commandTrigger.getCommandClass())
                ));
    }

    public void executeCommand(String input) {
        Command command = getCommand(input);
        command.execute();
    }

    public Command getCommand(String input) {
        if (commands == null) init();
        String commandInput = getCommandNameFromString(input);
        Command command = commands.get(CommandTrigger.of(commandInput));

        if (command instanceof ParameterizedCommand parameterizedCommand) {
            String parameter = getParameterFromString(input);
            parameterizedCommand.setParameter(parameter);
        }
        return command;
    }
    private String getCommandNameFromString(String input) {
        String[] strings = input.split("\\s", 2);
        return strings[0].trim();
    }

    private String getParameterFromString(String input) {
        String parameter = null;
        String[] strings = input.split("\\s", 2);
        if (strings.length > 1) {
            parameter = strings[1].trim();
        }
        return parameter;
    }

}
