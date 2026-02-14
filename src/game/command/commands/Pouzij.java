package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.item.Item;
import game.room.Room;
import game.ui.font;


public class Pouzij extends Command {
    private boolean exit;

    public Pouzij() {
        exit = false;
    }

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

    @Override
    public boolean exit() {
        return exit;
    }
}
