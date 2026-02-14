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
            Room newItemLocation = world.getPlayerRoom();
            Item itemToPlace = player.getInventory();

            //Nastavení lokaci itemu na lokaci, kde je hráč
            itemToPlace.setLocation(newItemLocation.getName());

            if (world.getRooms().size() > 1 && world.getItems().size() > 1) {
                //Jestli se položil první a poslední item (podle JSON struktury) na jeho správnou pozici (Pojistka na Chodbu a Baterie do Strojovny), tak se "nainstaluje" a už nebude moci být znovu pohnutelný

                //Jestli je první item (Pojistka) na druhé pozici (Chodba), toto celé vychází ze struktury JSONu
                if (itemToPlace.equals(world.getItems().getFirst()) && newItemLocation.equals(world.getRooms().get(1))) {
                    itemToPlace.setMovable(false); //Item se "nainstaluje"

                    if (world.getCharacters().size() > 2) {
                        world.getCharacters().get(1).progressMonologue(); //Xel
                        world.getCharacters().get(2).progressMonologue(); //Orion
                    }

                    return "Nainstalovali jste item " + itemToPlace.getName() + "!";
                }

                //Jestli je poslední item (Baterie) na poslední pozici (Strojovna), toto celé znovu vychází ze struktury JSONu
                if (itemToPlace.equals(world.getItems().getLast()) && newItemLocation.equals(world.getRooms().getLast())) {
                    itemToPlace.setMovable(false); //Item se "nainstaluje"

                    //TODO: monolog
                    return "Nainstalovali jste item " + itemToPlace.getName() + "!";
                }
            }

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
