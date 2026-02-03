package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;
import game.room.Room;
import game.item.Item;

import game.ui.UI;

import java.util.ArrayList;


public class Zkombinovat extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        String playerLocation = UI.toLowercaseAscii(world.getPlayerRoom().getName());

        //Předposlední místnost v JSONu představuje místnost, ve které lze použít právě příkaz Zkombinovat
        Room secondToLastRoom = world.getRooms().get(world.getRooms().size() - 2);
        String secondToLastRoomLocation = UI.toLowercaseAscii(secondToLastRoom.getName());

        if (playerLocation.equals(secondToLastRoomLocation)) {
            //Předposlední 3 itemy v JSONu se mohou zkombinovat a tím dostane hráč
            ArrayList<Item> itemsNeeded = new ArrayList<>();

            for (int i = 0; i < world.getItems().size(); i++) {
                if (world.getItems().get(i).isCombinable()) {
                    itemsNeeded.add(world.getItems().get(i));
                }
            }

            if (itemsNeeded.isEmpty()) return "Neexistují žádné kombinatovatelné itemy!";

            for (int i = 0; i < itemsNeeded.size(); i++) {

            }
        }
        return "Nenacházíte se v místnosti \"" + secondToLastRoom.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
