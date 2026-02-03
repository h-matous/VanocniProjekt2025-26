package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.room.Room;
import game.item.Item;

public class Poloz extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        if (player.getInventory() != null) {
            Room newItemLocation;

            newItemLocation = world.getPlayerRoom();


            Item itemToPlace = player.getInventory();

            //Nastavení lokaci itemu na lokaci, kde je hráč
            itemToPlace.setLocation(newItemLocation.getName());

            //Sebrání itemu z inventáře hráče
            player.setInventory(null);

            return "Položili jste item \"" + itemToPlace.getName() + "\" do místnosti \"" + newItemLocation.getName() + "\".";
        }

        return "Hráč nemá v inventáři žádný item.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
