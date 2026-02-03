package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;
import game.item.Item;

import game.room.Room;
import game.ui.UI;

import java.util.ArrayList;

public class Prozkoumat extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        StringBuilder toReturn = new StringBuilder();

        if (areAnyMovableItemsInPlayerRoom(world)) {
            toReturn.append("Hmmm, tak v této místnosti vidím itemy: ");
            toReturn.append(movableItemsInPlayerRoomText(world));

            if (areAnyNonMovableInteractableItemsInPlayerRoom(world)) {
                toReturn.append("\n");
                toReturn.append("A je tu dokonce použitelný: ");
                toReturn.append(NonMovableInteractableItemsInPlayerRoomText(world));
                toReturn.append("\n");
            }
        }
        else {
            toReturn.append("Žádné předměty v této místnosti nevidím...");

            if (areAnyNonMovableInteractableItemsInPlayerRoom(world)) {
                toReturn.append("\n");
                toReturn.append("Ale je zde alespoň použitelný: ");
                toReturn.append(NonMovableInteractableItemsInPlayerRoomText(world));
                toReturn.append("\n");
            }
        }
        toReturn.append("\n");


        if (areAnyCharactersInPlayerRoom(world)) {
            toReturn.append("Jsou zde postavy s kterými bych si mohl promluvit: ");
            toReturn.append(charactersInPlayerRoomText(world));
        }
        else {
            toReturn.append("Nejsou tady žádné postavy s kterými bych si mohl promluvit.");
        }
        toReturn.append("\n");


        String playerLocation = UI.toLowercaseAscii(world.getPlayerRoom().getName());

        //Předposlední místnost v JSONu představuje místnost, ve které lze použít právě příkaz Zkombinovat
        Room secondToLastRoom = world.getRooms().get(world.getRooms().size() - 2);
        String secondToLastRoomLocation = UI.toLowercaseAscii(secondToLastRoom.getName());

        if (playerLocation.equals(secondToLastRoomLocation)) {
            toReturn.append("V této místnosti bych možná mohl nějaké itemy zkombinovat...");
            toReturn.append("\n");
        }

        return toReturn.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    private String getStringOfItemsText(ArrayList<Item> items) {
        StringBuilder toReturn = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            toReturn.append(items.get(i).getName());

            if (i < items.size() - 1) {
                toReturn.append(", ");
            }
        }

        return toReturn.toString();
    }


    private ArrayList<Item> NonMovableInteractableItemsInPlayerRoom(GameData world) {
        ArrayList<Item> toReturn = new ArrayList<>();

        String playerLocation = UI.toLowercaseAscii(world.getPlayerRoom().getName());

        for (int i = 0; i < world.getItems().size(); i++) {
            Item currentItem = world.getItems().get(i);
            String currentItemLocation = UI.toLowercaseAscii(currentItem.getLocation());

            //Jestli se rovnají názvy lokace hráče a lokace itemů
            if (playerLocation.equals(currentItemLocation)) {
                //Jestli je item zároveň nemovitý a použitelný
                if (!currentItem.isMovable() && currentItem.isInteractable()) {
                    //Bere v potaz, že právě nemovitý item je zároveň použitelný
                    toReturn.add(currentItem);
                }
            }
        }

        return toReturn;
    }

    private boolean areAnyNonMovableInteractableItemsInPlayerRoom(GameData world) {
        return !NonMovableInteractableItemsInPlayerRoom(world).isEmpty();
    }

    private String NonMovableInteractableItemsInPlayerRoomText(GameData world) {
        return getStringOfItemsText(NonMovableInteractableItemsInPlayerRoom(world));
    }


    private ArrayList<Item> movableItemsInPlayerRoom(GameData world) {
        //Jen které jsou movité (ty nemovité zároveň jakoby nejsou itemy, ale v backendu jsou)
        ArrayList<Item> toReturn = new ArrayList<>();

        String playerLocation = UI.toLowercaseAscii(world.getPlayerRoom().getName());

        for (int i = 0; i < world.getItems().size(); i++) {
            Item currentItem = world.getItems().get(i);
            String currentItemLocation = UI.toLowercaseAscii(currentItem.getLocation());

            //Jestli se rovnají názvy lokace hráče a lokace itemů
            if (playerLocation.equals(currentItemLocation)) {
                //Jestli je item zároveň movitý
                if (currentItem.isMovable()) {
                    toReturn.add(currentItem);
                }
            }
        }

        return toReturn;
    }

    private boolean areAnyMovableItemsInPlayerRoom(GameData world) {
        //Jen pro movité
        return !movableItemsInPlayerRoom(world).isEmpty();
    }

    private String movableItemsInPlayerRoomText(GameData world) {
        //Jen pro movité
        return getStringOfItemsText(movableItemsInPlayerRoom(world));
    }


    private boolean areAnyCharactersInPlayerRoom(GameData world) {
        for (int i = 0; i < world.getCharacters().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getCharacters().get(i).getLocation()))) {
                return true;
            }
        }

        return false;
    }

    private String charactersInPlayerRoomText(GameData world) {
        StringBuilder toReturn = new StringBuilder();

        //Number of Items in the same room as the player
        int itemCount = 0;

        for (int i = 0; i < world.getCharacters().size(); i++) {

            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getCharacters().get(i).getLocation()))) {
                toReturn.append(world.getCharacters().get(i).getName());
                itemCount++;

                if (i < itemCount - 1) {
                    toReturn.append(", ");
                }
            }

        }

        return toReturn.toString();
    }
}