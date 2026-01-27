package game.command.commands;

import game.command.Command;

import game.GameData;
import game.spaceship.Room;
import game.ui.UI;

public class Prozkoumat extends Command {
    @Override
    public String execute(String param, GameData world) {
        return "Hmmm, tak v této místnosti vidím itemy: " + itemsInPlayerRoomText(world) + "\n";
    }
    //TODO: a je zde postava if nejaka tam je

    @Override
    public boolean exit() {
        return false;
    }

    private String itemsInPlayerRoomText(GameData world) {
        String toReturn = "";

        for (int i = 0; i < world.getItems().size(); i++) {

            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                toReturn = toReturn + world.getItems().get(i);

                if (i < world.getItems().size() - 1) {
                    toReturn = toReturn + ", ";
                }
            }

        }

        return toReturn;
    }
}
