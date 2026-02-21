package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.item.Item;
import game.room.Room;
import game.ui.font;

public class Vezmi extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        if (player.getInventory() == null) {

            Item itemToPickUp;
            Room itemLocation;
            try {
                itemToPickUp = world.getItemFromParam(param);
            }
            catch (IllegalArgumentException e) {
                //Item s tímto názvem prostě neexistuje v JSONu
                return e.getMessage();
            }
            try {
                itemLocation = world.getItemLocationFromItem(itemToPickUp);
            }
            catch (IllegalArgumentException e) {
                //Hledaný Item se právě nenachází na mapě (např. nebyl ještě vyroben kombinací, nebo už byl instalován např. Pojistka do Chodby)
                return "Item " + itemToPickUp.getName() + " je právě nedostupný.";
            }


            if (world.getPlayerRoom().equals(itemLocation)) {
                if (itemToPickUp.isMovable()) {
                    itemToPickUp.setLocation("");
                    player.setInventory(itemToPickUp);
                    return font.pink() + "Sebrali jste item: " + itemToPickUp.getName() + font.reset();
                }

                return "Předmět \"" + itemToPickUp.getName() + "\" není movitý.";
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
