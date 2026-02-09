package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.item.Item;
import game.room.Room;

public class Vezmi extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        if (player.getInventory() == null) {

            Item itemToPickUp;
            Room itemLocation;
            try {
                itemToPickUp = world.getItemFromParam(param);
                itemLocation = world.getItemLocationFromItem(itemToPickUp);
            }
            catch (IllegalArgumentException e) {
                return e.getMessage();
            }


            if (world.getPlayerRoom().equals(itemLocation)) {
                if (itemToPickUp.isMovable()) {
                    itemToPickUp.setLocation("");
                    player.setInventory(itemToPickUp);
                    return "Sebrali jste item: " + itemToPickUp.getName();
                }

                return "Item \"" + itemToPickUp.getName() + "\" není movitý.";
            }

            return "Hráč se nenachází ve stejné místnosti jako item!";
        }

        return "Hráč už má plný inventář!";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
