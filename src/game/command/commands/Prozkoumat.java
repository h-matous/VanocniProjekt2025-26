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


        return toReturn.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }


    private boolean areAnyNonMovableInteractableItemsInPlayerRoom(GameData world) {
        for (int i = 0; i < world.getItems().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                if (!world.getItems().get(i).isMovable() && world.getItems().get(i).isInteractable()) {
                    //Bere v potaz, že právě nemovitý item je zároveň použitelný
                    return true;
                }
            }
        }

        return false;
    }

    private String NonMovableInteractableItemsInPlayerRoomText(GameData world) {
        StringBuilder toReturn = new StringBuilder();

        //Number of Items in the same room as the player
        int itemCount = 0;

        for (int i = 0; i < world.getItems().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                if (!world.getItems().get(i).isMovable() && world.getItems().get(i).isInteractable()) {
                    itemCount++;
                }
            }
        }

        for (int i = 0; i < world.getItems().size(); i++) {

            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                if (!world.getItems().get(i).isMovable() && world.getItems().get(i).isInteractable()) {
                    toReturn.append(world.getItems().get(i).getName());

                    if (i < itemCount - 1) {
                        toReturn.append(", ");
                    }
                }
            }

        }

        return toReturn.toString();
    }

    //Které jsou movité
    private boolean areAnyItemsInPlayerRoom(GameData world) {
        for (int i = 0; i < world.getItems().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                if (world.getItems().get(i).isMovable()) {
                    return true;
                }
            }
        }

        return false;
    }

    //Jen pro movité
    private String itemsInPlayerRoomText(GameData world) {
        StringBuilder toReturn = new StringBuilder();

        //Number of Items in the same room as the player
        int itemCount = 0;

        for (int i = 0; i < world.getItems().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                if (world.getItems().get(i).isMovable()) {
                    itemCount++;
                }
            }
        }

        for (int i = 0; i < world.getItems().size(); i++) {

            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getItems().get(i).getLocation()))) {
                if (world.getItems().get(i).isMovable()) {
                    toReturn.append(world.getItems().get(i).getName());

                    if (i < itemCount - 1) {
                        toReturn.append(", ");
                    }
                }
            }

        }

        return toReturn.toString();
    }
    //TODO: jak je if(i < itemCount -1), opravit ze nahazim vsechny itemy ve stejny mistnosti jako hrac do arraylistu a v tom budu iterovat, takhle se nekdy napise bez těch čárek mezi elementy
    //TODO: nebo vlastně to je možná kvůli tomu, že to tam je jeden item bez lokace, kterej právě drží hráč v inventáři

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