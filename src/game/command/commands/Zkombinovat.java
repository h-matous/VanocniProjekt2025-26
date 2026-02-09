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

        //Předposlední 4 itemy v JSONu se mohou zkombinovat a tím dostane hráč baterii
        ArrayList<Item> itemsNeeded = loadCombinableItemsNeeded(world);

        if (itemsNeeded.isEmpty()) return "Neexistují žádné kombinovatelné itemy!";
        if (world.getRooms().size() < 2) return "Chybný JSON! Musí v něm být obsaženo více jak 1 Item, aby se dal použít příkaz zkombinovat!";

        if (!world.isFirstProgressingPhaseDone()) return "Nelze kombinovat Itemy... Není dokončen první požadavek!" + "\n" + world.getEndingPhasesRequirementMessages().getFirst();

        //Předposlední místnost v JSONu představuje místnost, ve které lze použít právě příkaz Zkombinovat
        Room secondToLastRoom = world.getRooms().get(world.getRooms().size() - 2);
        String secondToLastRoomLocation = UI.toLowercaseAscii(secondToLastRoom.getName());

        if (playerLocation.equals(secondToLastRoomLocation)) {
            try {
                for (int i = 0; i < itemsNeeded.size(); i++) {
                    Item currentItem = itemsNeeded.get(i);

                    if (!secondToLastRoomLocation.equals(UI.toLowercaseAscii(currentItem.getLocation()))) {
                        return "V místnosti " + secondToLastRoom.getName() + " nejsou položené všechny itemy potřebné ke kombinaci: " + world.getStringOfItemsText(itemsNeeded) + "!";
                    }
                }
            }
            catch (IllegalArgumentException e) {
                return e.getMessage() + " Chybný JSON!";
            }


            makeCombinedItemsDisappear(itemsNeeded);

            //Podle JSON struktury je poslední Item právě craftable
            Item craftedItem = world.getItems().get(world.getItems().size() - 1);
            craftedItem.setLocation(secondToLastRoom.getName());

            return "Vyrobili jste item: " + craftedItem.getName();
        }
        return "Nenacházíte se v místnosti \"" + secondToLastRoom.getName() + "\"!";
    }


    private ArrayList<Item> loadCombinableItemsNeeded(GameData world) {
        ArrayList<Item> toReturn = new ArrayList<>();

        for (int i = 0; i < world.getItems().size(); i++) {
            if (world.getItems().get(i).isCombinable()) {
                toReturn.add(world.getItems().get(i));
            }
        }

        return toReturn;
    }

    private void makeCombinedItemsDisappear(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);

            currentItem.setLocation("");
            currentItem.setCombinable(false);
            currentItem.setInteractable(false);
            currentItem.setMovable(false);
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
