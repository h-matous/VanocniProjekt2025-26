package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;
import game.item.Item;

import game.room.Room;
import game.ui.UI;
import game.ui.font;

import java.util.ArrayList;

/**
 * Třída Prozkoumat reprezentuje Command, který umožňuje Hráči prozkoumat Místnost ve které se právě nachází a tím zjistit, zdali se v ní nachází ještě nějaké Itemy, Postavy a nebo jestli je ta Místnost něčím speciální
 */
public class Prozkoumat extends Command {
    /**
     * Metoda execute u příkazu Prozkoumat vypíše všechny zajímavosti, které nabízí Místnost ve které se právě Hráč nachází
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        StringBuilder toReturn = new StringBuilder();

        addItemsText(toReturn, world);

        addCharactersText(toReturn, world);

        addCombinationalItemsText(toReturn, world);

        return toReturn.toString();
    }


    /**
     * Slouží k získání informací o Itemech/Předmětech, jestli v Hráčově místnosti nějaké vůbec jsou
     * @param input StringBuilder do kterého se tento text bude přidávat
     * @param world instance Herního světa
     */
    public void addItemsText(StringBuilder input, GameData world) {
        if (areAnyMovableItemsInPlayerRoom(world)) {
            input.append(font.yellow());
            input.append("Hmmm, tak v této místnosti vidím Itemy: ");
            input.append(movableItemsInPlayerRoomText(world));
            input.append(font.reset());

            if (areAnyNonMovableInteractableItemsInPlayerRoom(world)) {
                input.append("\n");
                input.append(font.orange());
                input.append("A je tu dokonce použitelný: ");
                input.append(NonMovableInteractableItemsInPlayerRoomText(world));
                input.append(font.reset());
                input.append("\n");
            }
        }
        else {
            input.append("Žádné Itemy v této místnosti nevidím...");

            if (areAnyNonMovableInteractableItemsInPlayerRoom(world)) {
                input.append("\n");
                input.append(font.orange());
                input.append("Ale je zde alespoň použitelný: ");
                input.append(NonMovableInteractableItemsInPlayerRoomText(world));
                input.append(font.reset());
                input.append("\n");
            }
        }
        input.append("\n");
    }

    /**
     * Slouží k získání informací o Postavách, jestli v Hráčově místnosti nějaké vůbec jsou
     * @param input StringBuilder do kterého se tento text bude přidávat
     * @param world instance Herního světa
     */
    public void addCharactersText(StringBuilder input, GameData world) {
        if (areAnyCharactersInPlayerRoom(world)) {
            input.append(font.cyan());
            input.append("Jsou zde postavy s kterými bych si mohl promluvit: ");
            input.append(charactersInPlayerRoomText(world));
            input.append(font.reset());
        }
        else {
            input.append("Nejsou tady žádné postavy s kterými bych si mohl promluvit.");
        }
        input.append("\n");
    }

    /**
     * Provede kontrolu, zdali se Hráč nachází v kombinační Místnosti a poté přidá informaci, že lze v této Místnosti možná nějaké Itemy zkombinovat
     * @param input StringBuilder do kterého se tento text bude přidávat
     * @param world instance Herního světa
     */
    public void addCombinationalItemsText(StringBuilder input, GameData world) {
        String playerLocation = UI.toLowercaseAscii(world.getPlayerRoom().getName());

        if (world.getRooms().size() >= 3) {
            //Předposlední místnost v JSONu představuje místnost, ve které lze použít právě příkaz Zkombinovat
            Room secondToLastRoom = world.getRooms().get(world.getRooms().size() - 2);
            String secondToLastRoomLocation = UI.toLowercaseAscii(secondToLastRoom.getName());

            if (playerLocation.equals(secondToLastRoomLocation)) {
                input.append(font.orange());
                input.append(font.bold());
                input.append("V této místnosti bych možná mohl nějaké Itemy zkombinovat...");
                input.append(font.reset());
                input.append("\n");
            }
        }
    }


    /**
     * Slouží k získání ArrayListu všech nemovitých, ale Použitelných Předmětů v Místnosti, kde je Hráč
     * @param world instance Herního světa
     * @return vrací ArrayList těchto Itemů
     */
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

    /**
     * Slouží k zjištění, zdali je v Hráčově Místnosti alespoň jeden nemovitý, ale Použitelný Předmět
     * @param world instance Herního světa
     * @return vrací boolean, jestli vážně nějaké takové Předměty jsou v Hráčově Místnosti
     */
    private boolean areAnyNonMovableInteractableItemsInPlayerRoom(GameData world) {
        return !NonMovableInteractableItemsInPlayerRoom(world).isEmpty();
    }

    /**
     * Získá text String všech nemovitých, ale Použitelných Předmětů v Hráčově Místnosti
     * @param world instance Herního světa
     * @return vrací String, který obsahuje tyto Předměty vylistované
     */
    private String NonMovableInteractableItemsInPlayerRoomText(GameData world) {
        return world.getStringOfItemsText(NonMovableInteractableItemsInPlayerRoom(world));
    }


    /**
     * Slouží k získání ArrayListu všech movitých Itemů v Místnosti, kde je Hráč
     * @param world instance Herního světa
     * @return vrací ArrayList těchto Itemů
     */
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

    /**
     * Slouží k zjištění, zdali je v Hráčově Místnosti alespoň jeden movitý Item
     * @param world instance Herního světa
     * @return vrací boolean, jestli vážně nějaké takové Itemy jsou v Hráčově Místnosti
     */
    private boolean areAnyMovableItemsInPlayerRoom(GameData world) {
        //Jen pro movité
        return !movableItemsInPlayerRoom(world).isEmpty();
    }

    /**
     * Získá text String všech movitých Itemů v Hráčově Místnosti
     * @param world instance Herního světa
     * @return vrací String, který obsahuje tyto Itemy vylistované
     */
    private String movableItemsInPlayerRoomText(GameData world) {
        //Jen pro movité
        return world.getStringOfItemsText(movableItemsInPlayerRoom(world));
    }

    /**
     * Slouží k zjištění, zdali je v Hráčově Místnosti alespoň jedna Postava
     * @param world instance Herního světa
     * @return vrací boolean, jestli je vážně v Hráčově Místnosti nějaká Postava
     */
    private boolean areAnyCharactersInPlayerRoom(GameData world) {
        for (int i = 0; i < world.getCharacters().size(); i++) {
            if (UI.toLowercaseAscii(world.getPlayerRoom().getName()).equals(UI.toLowercaseAscii(world.getCharacters().get(i).getLocation()))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Získá text String všech Postav v Hráčově Místnosti
     * @param world instance Herního světa
     * @return vrací String, který obsahuje tyto Postavy vylistované
     */
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

    /**
     * Metoda exit() zjišťuje, jestli po zavolání tohoto příkazu má hra zrovna skončit, v tomto případě nemá
     * @return boolean jestli má hra po tomto příkazu skončit (false)
     */
    @Override
    public boolean exit() {
        return false;
    }

}