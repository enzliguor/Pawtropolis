package pawtropolis.complex.game.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.complex.game.command.domain.Command;
import pawtropolis.complex.game.command.domain.ParameterizedCommand;

@Slf4j
@Component
public class CommandManager {

    private final ApplicationContext applicationContext;

    @Autowired
    private CommandManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void execute(String input){
        Command command = getCommand(input);
        command.execute();
    }
    public Command getCommand(String input){
        String commandInput = getCommandFromString(input);
        Command command;
        try{
            command = applicationContext.getBean(commandInput, Command.class);
        }catch (NoSuchBeanDefinitionException exception){
            command = applicationContext.getBean("wrongCommand", Command.class);
        }
        if(command instanceof ParameterizedCommand parameterizedCommand){
            String parameter = getParameterFromString(input);
            parameterizedCommand.setParameter(parameter);
        }
        return command;
    }

    private String getCommandFromString(String input){
        String [] strings = input.split("\\s", 2);
        return strings[0].trim().toLowerCase();
    }

    private String getParameterFromString(String input){
        String parameter = null;
        String [] strings = input.split("\\s", 2);
        if( strings.length>1){
            parameter =  strings[1].trim();
        }
        return parameter;
    }

}
