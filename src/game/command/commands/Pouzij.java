package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.item.Item;
import game.room.Room;
import game.ui.font;


/**
 * Třída Pouzij reprezentuje Command, který umožňuje Hráči „Použít“ použitelné předměty
 */
public class Pouzij extends Command {
    private boolean exit;

    /**
     * Konstruktor nastaví exit na false,
     * Ze začátku tento příkaz hru ukončovat nebude
     */
    public Pouzij() {
        exit = false;
    }

    /**
     * Metoda execute u příkazu Pouzij kontroluje jestli se Hráč může vůbec daný předmět použít a následně spustí finální hádací minihru
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {

        Item itemToUse;
        Room itemLocation;
        try {
            itemToUse = world.getItemFromParam(param);

            //Jestli item není použitelný
            if (!itemToUse.isInteractable()) {
                return "Tento předmět nelze použít...";
            }

            itemLocation = world.getItemLocationFromItem(itemToUse);

            //Jestli je hráč není ve stejné místnosti jako je použitelný item
            if (!world.getPlayerRoom().equals(itemLocation)) {
                return "Předmět nelze použít, hráč se nenachází ve stejné místnosti jako předmět...";
            }
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        //Check jestli je alespoň pojistka na svým místě (1. progresová fáze)
        if (!world.isFirstProgressingPhaseDone()) return "Nelze použít..." + "\n" + "Není dokončen první požadavek!" + "\n" + font.magenta() + world.getEndingPhasesRequirementMessages().get(0) + font.reset();

        //Check jestli je na konci hry, jestli je baterka na svým místě (2. progresová fáze)
        if (!world.isSecondProgressingPhaseDone()) return "Nelze použít..." + "\n" + "Není dokončen druhý požadavek!" + "\n" + font.magenta() + world.getEndingPhasesRequirementMessages().get(1) + font.reset();

        //Zavolání metody na guessing minihru
        world.playFinalGuessingMinigame();
        exit = true;


        //Konec hry, hráč vítězí!
        return world.getEndingStory();
    }

    /**
     * Metoda exit() zjišťuje, jestli po zavolání tohoto příkazu má hra zrovna skončit, v tomto případě má pouze pokud Hráč dokončil hádací minihru a zvítězil
     * @return boolean jestli má hra po tomto příkazu skončit (false/true)
     */
    @Override
    public boolean exit() {
        return exit;
    }
}
