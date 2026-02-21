package game.command;

import game.GameData;
import game.ui.UI;
import game.ui.font;

import java.util.Random;

/**
 * Třída finalGuessingMinigame, obsahuje metodu play() pro spuštění minihry, která zakončuje celou hru
 */
public class finalGuessingMinigame {

    private final GameData world;
    private final UI ui;
    private final Random random;

    private final int minNumber;
    private final int maxNumber;

    /**
     * Konstruktor pro nastavení hodnot
     * @param world Herní svět
     * @param ui Uživatelské rozhraní
     */
    public finalGuessingMinigame(GameData world, UI ui) {
        this.world = world;
        this.ui = ui;

        random = new Random();

        //[MHz] X-band (8-12 GHz)
        minNumber = 8000;
        maxNumber = 12000;
    }

    /**
     * Metoda play() slouží k spuštění konečné minihry v které Hráč hádá správné číslo
     */
    public void play() {
        int cislo = random.nextInt(minNumber, maxNumber);

        int hracovoCislo = -1;

        while (hracovoCislo != cislo) {
            ui.println(world.getEndingPhaseGuessingMinigameMessage());
            ui.scanNextLine();

            try {
                hracovoCislo = Integer.parseInt(ui.getLastString());

                if (hracovoCislo < cislo) ui.println(font.lightBlue() + font.bold() + "Hádané číslo je větší!" + font.reset());
                if (hracovoCislo > cislo) ui.println(font.lightRed() + font.bold() + "Hádané číslo je menší!" + font.reset());
            }
            catch (NumberFormatException e) {
                ui.println(font.red() + font.italic() + "Nezadali jste platné číslo!" + font.reset());
            }

            ui.print("\n");
        }

        ui.print("\n");
        ui.println(font.cyan() + font.bold() + "Správně jste uhodli číslo: " + cislo + "!" + font.reset());
        ui.println("\n");
    }
}
