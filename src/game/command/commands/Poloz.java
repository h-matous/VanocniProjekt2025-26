package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.room.Room;
import game.item.Item;
import game.ui.font;


/**
 * Třída Poloz reprezentuje Command, který umožňuje Hráči položit Item z jeho inventáře do Místnosti, kde se nachází
 */
public class Poloz extends Command {
    /**
     * Metoda execute u příkazu Poloz vezme Item z Hráčova inventáře a položí ho do Místnosti, kde právě je a pokud se Item „nainstaluje“ tak se progresuje monolog u Postav
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        if (player.getInventory() != null) {
            Room newItemLocation = world.getPlayerRoom();
            Item itemToPlace = player.getInventory();

            //Nastavení lokaci itemu na lokaci, kde je hráč
            itemToPlace.setLocation(newItemLocation.getName());

            if (world.getRooms().size() > 1 && world.getItems().size() > 1) {
                //Jestli se položil první a poslední item (podle JSON struktury) na jeho správnou pozici (Pojistka na Chodbu a Baterie do Strojovny), tak se "nainstaluje" a už nebude moci být znovu pohnutelný

                //Jestli je první item (Pojistka) na druhé pozici (Chodba), toto celé vychází ze struktury JSONu
                //Jestli je poslední item (Baterie) na poslední pozici (Strojovna), toto celé znovu vychází ze struktury JSONu
                if (itemToPlace.equals(world.getItems().getFirst()) && newItemLocation.equals(world.getRooms().get(1)) || itemToPlace.equals(world.getItems().getLast()) && newItemLocation.equals(world.getRooms().getLast())) {
                    itemToPlace.setMovable(false); //Item se "nainstaluje"
                    player.setInventory(null); //Sebrání itemu z inventáře hráče

                    if (!world.getCharacters().isEmpty()) {
                        for (int i = 0; i < world.getCharacters().size(); i++) {
                            world.getCharacters().get(i).progressMonologue(); //Progres monologu u postav: Orion, Xel, R5
                        }
                    }

                    return font.yellow() + "Nainstalovali jste item " + font.bold() + itemToPlace.getName() + font.reset() + font.yellow() + "!" + font.reset();
                }
            }

            //Sebrání itemu z inventáře hráče
            player.setInventory(null);

            return "Položili jste item \"" + itemToPlace.getName() + "\" do místnosti \"" + newItemLocation.getName() + "\".";
        }

        return "Hráč nemá v inventáři žádný item.";
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
