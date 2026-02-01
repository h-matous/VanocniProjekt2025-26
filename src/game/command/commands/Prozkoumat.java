package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.ui.UI;

public class Prozkoumat extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        StringBuilder toReturn = new StringBuilder();

        if (areAnyItemsInPlayerRoom(world)) {
            toReturn.append("Hmmm, tak v této místnosti vidím itemy: ");
            toReturn.append(itemsInPlayerRoomText(world));
        }
        else {
            toReturn.append("Žádné předměty v této místnosti nevidím...");
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


        return toReturn.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    private boolean areAnyItemsInPlayerRoom(GameData world) {
        for (int i = 0; i < world.getItems().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                return true;
            }
        }

        return false;
    }

    //TODO: ošetřit nepohyblivé itemy (Panel v komunikacích) a vytisknout je na nový řádek, něco jako sice tu nevidím žádné itemy, ale je tu nějaký panel... bla bla bla
    private String itemsInPlayerRoomText(GameData world) {
        StringBuilder toReturn = new StringBuilder();

        //Number of Items in the same room as the player
        int itemCount = 0;

        for (int i = 0; i < world.getItems().size(); i++) {

            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                toReturn.append(world.getItems().get(i).getName());
                itemCount++;

                if (i < itemCount - 1) {
                    toReturn.append(", ");
                }
            }

        }

        return toReturn.toString();
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