package pawtropolis.complex.game.command.domain;

import pawtropolis.complex.game.map.maploader.MapInitializer;

public abstract class ParameterizedCommand extends Command {

    protected String parameter;

    protected ParameterizedCommand(MapInitializer mapInitializer) {
        super(mapInitializer);
    }

    public void setParameter(String parameter){
        this.parameter = parameter;
    }
}
