package pawtropolis.game.command.domain.gamecommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.game.domain.SaveBlockBO;
import pawtropolis.persistence.service.SaveBlockService;

@Component
public class SaveCommand extends GameCommand {

    private final SaveBlockService saveBlockService;

    @Autowired
    protected SaveCommand(SaveBlockBO saveBlockBO, SaveBlockService saveBlockService) {
        super(saveBlockBO);
        this.saveBlockService = saveBlockService;
    }

    @Override
    public void execute() {
        SaveBlockBO savedGameSession = this.saveBlockService.saveOrUpdate(this.saveBlockBO);
        if(this.saveBlockBO.isNotPersisted()){
            this.saveBlockBO.initializeGameSession(savedGameSession);
        }
    }
}
