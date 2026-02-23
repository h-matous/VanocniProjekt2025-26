package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;
import game.room.Room;
import game.item.Item;

import game.ui.UI;
import game.ui.font;

import java.util.ArrayList;

/**
 * Třída Zkombinovat reprezentuje Command, který umožňuje Hráči zkombinovat nějaké Itemy do jiného Itemu. Tento nový Item může být klíčový k dohrání hry
 */
public class Zkombinovat extends Command {
    /**
     * Metoda execute u příkazu Zkombinovat kontroluje, jestli jsou splněné podmínky pro kombinaci a poté následné zkombinování určitých Itemů do Itemu jiného
     * @param param String parametr, který byl uživatelem specifikován po příkazu
     * @param world instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        String playerLocation = UI.toLowercaseAscii(world.getPlayerRoom().getName());

        //Předposlední 4 itemy v JSONu se mohou zkombinovat a tím dostane hráč baterii
        ArrayList<Item> itemsNeeded = loadCombinableItemsNeeded(world);

        if (itemsNeeded.isEmpty()) return "Neexistují žádné kombinovatelné itemy!";
        if (world.getRooms().size() < 3) return "Chybný JSON! Musí obsahovat více jak 2 Místnosti, aby existovala Místnost, kde lze Itemy kombinovat!";
        if (world.getItems().size() < 2) return "Chybný JSON! Musí obsahovat více jak 1 Item, aby se dal použít příkaz zkombinovat!";

        //Předposlední místnost v JSONu představuje místnost, ve které lze použít právě příkaz Zkombinovat
        Room secondToLastRoom = world.getRooms().get(world.getRooms().size() - 2);
        String secondToLastRoomLocation = UI.toLowercaseAscii(secondToLastRoom.getName());

        if (!playerLocation.equals(secondToLastRoomLocation)) return "Nenacházíte se v místnosti \"" + secondToLastRoom.getName() + "\"!";
        //Check jestli je Pojistka na svým místě (1. progresová fáze)
        if (!world.isFirstProgressingPhaseDone()) return "Nelze použít..." + "\n" + "Není dokončen první požadavek!" + "\n" + font.magenta() + world.getEndingPhasesRequirementMessages().getFirst() + font.reset();

        try {
            for (int i = 0; i < itemsNeeded.size(); i++) {
                Item currentItem = itemsNeeded.get(i);
                if (!secondToLastRoomLocation.equals(UI.toLowercaseAscii(currentItem.getLocation()))) {
                    return font.magenta() + "V místnosti " + secondToLastRoom.getName() + " nejsou položené všechny itemy potřebné ke kombinaci: " + world.getStringOfItemsText(itemsNeeded) + "!" + font.reset();
                }
            }
        }
        catch (IllegalArgumentException e) {
            return e.getMessage() + " Chybný JSON!";
        }

        makeCombinedItemsDisappear(itemsNeeded);
        //Podle JSON struktury je poslední Item právě craftable
        Item craftedItem = world.getItems().getLast();
        craftedItem.setLocation(secondToLastRoom.getName());

        return font.orange() + "Vyrobili jste item: " + craftedItem.getName() + font.reset();
    }


    /**
     * Slouží k získání ArrayListu všech Itemů, které se používájí ke kombinaci
     * @param world Herní svět
     * @return vrací ArrayList Itemů, které jsou kombinovatelné
     */
    private ArrayList<Item> loadCombinableItemsNeeded(GameData world) {
        ArrayList<Item> toReturn = new ArrayList<>();

        for (int i = 0; i < world.getItems().size(); i++) {
            if (world.getItems().get(i).isCombinable()) {
                toReturn.add(world.getItems().get(i));
            }
        }

        return toReturn;
    }

    /**
     * Slouží k "schování" všech zkombinovaných Itemů, po tom co je Hráč zkombinuje do jiného Itemu
     * @param items ArrayList všech Itemů, které byly nutné ke kombinaci
     */
    private void makeCombinedItemsDisappear(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);

            currentItem.setLocation("");
            currentItem.setCombinable(false);
            currentItem.setInteractable(false);
            currentItem.setMovable(false);
        }
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
