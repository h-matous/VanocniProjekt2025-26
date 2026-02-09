package game.command;

import game.GameData;
import game.ui.UI;

import java.util.Random;

public class finalGuessingMinigame {

    private GameData world;
    private UI ui;
    private Random random;

    public finalGuessingMinigame(GameData world, UI ui) {
        this.world = world;
        this.ui = ui;

        random = new Random();
    }

    public void play() {
        int cislo = random.nextInt(10000, 100000);

        while (!ui.getLastString().equals(String.valueOf(cislo))) {
            ui.println(world.getEndingPhaseGuessingMinigameMessage());
            ui.scanNextLine();

            int hracovoCislo = Integer.parseInt(ui.getLastString());

            if (hracovoCislo < cislo) ui.println("Hádané číslo je větší!");
            if (hracovoCislo > cislo) ui.println("Hádané číslo je menší!");
        }
    }
}
