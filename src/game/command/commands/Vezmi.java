package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.item.Item;
import game.room.Room;
import game.ui.font;

/**
 * Třída Vezmi reprezentuje Command, který umožňuje Hráči vzít vybraný Item do inventáře. Tento Item může tedy přenášet po mapě
 */
public class Vezmi extends Command {
    /**
     * Metoda execute u příkazu Vezmi kontroluje jestli Hráč tento Item může sebrat a následně mu ho přidělí do inventáře
     * @param param String parametr, který byl uživatelem specifikován po příkazu
     * @param world instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
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

    /**
     * Metoda exit() zjišťuje, jestli po zavolání tohoto příkazu má hra zrovna skončit, v tomto případě nemá
     * @return boolean jestli má hra po tomto příkazu skončit (false)
     */
    @Override
    public boolean exit() {
        return false;
    }
}
