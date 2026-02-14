package game.command;

import game.GameData;
import game.ui.UI;
import game.ui.font;

import java.util.Random;

public class finalGuessingMinigame {

    private final GameData world;
    private final UI ui;
    private final Random random;

    public finalGuessingMinigame(GameData world, UI ui) {
        this.world = world;
        this.ui = ui;

        random = new Random();
    }

    public void play() {
        //X-band (8-12 GHz)
        int cislo = random.nextInt(8000000, 12000000); //kHz

        while (!ui.getLastString().equals(String.valueOf(cislo))) {
            ui.println(world.getEndingPhaseGuessingMinigameMessage());
            ui.scanNextLine();

            int hracovoCislo = Integer.parseInt(ui.getLastString());

            if (hracovoCislo < cislo) ui.println("Hádané číslo je větší!");
            if (hracovoCislo > cislo) ui.println("Hádané číslo je menší!");
        }

        ui.println(font.cyan() + font.bold() + "Správně jste uhodli číslo: " + cislo + "!" + font.reset());
    }
}
