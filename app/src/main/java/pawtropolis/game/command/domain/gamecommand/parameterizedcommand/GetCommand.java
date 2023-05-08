package pawtropolis.game.command.domain.gamecommand.parameterizedcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pawtropolis.game.command.domain.gamecommand.BagCommand;
import pawtropolis.game.domain.GameSessionBO;
import pawtropolis.game.domain.ItemBO;
import pawtropolis.game.domain.PlayerBO;
import pawtropolis.game.domain.RoomBO;

@Slf4j
@Component
public class GetCommand extends ParameterizedCommand {

    private final BagCommand bagCommand;

    protected GetCommand(GameSessionBO gameSessionBO, BagCommand bagCommand) {
        super(gameSessionBO);
        this.bagCommand=bagCommand;
    }

    @Override
    public void execute() {
        RoomBO currentRoom = this.gameSessionBO.getCurrentRoom();
        PlayerBO player = this.gameSessionBO.getPlayer();
        ItemBO item = currentRoom.findItemByName(parameter);
        if (item == null) {
            log.info("\nItemBO not found\n");
        }else if(player.checkItemFitsInBag(item)){
            player.collectItem(item);
            currentRoom.removeItem(item);
            this.bagCommand.execute();
        }else{
            log.info("\nYour BagBO is too full! \n" +
                    "Free up " +
                    (item.getSlotsRequired() - player.getAvailableSlot()) +
                    " slots to get this item\n");
        }
    }
}
