package game;

import game.command.CommandHandler;
import game.ui.ConsoleUI;
import game.ui.UI;

import game.ui.Loader;
import game.ui.font;

import game.command.finalGuessingMinigame;


//TODO: dokončit gamedata.json přidat všechny monology

public class Game {
    private UI ui;
    private Player player;

    private CommandHandler cmdHandler;

    private GameData world;

    private finalGuessingMinigame finalMinigame;


    private final String gameDataResourcePath;


    public Game() {
        this.ui = new ConsoleUI();
        this.player = new Player();

        this.gameDataResourcePath = "gamedata.json"; //původně resource/gamedata.json, když se načítalo ze souboru

        try {
            this.world = Loader.loadGameData(gameDataResourcePath);
        }
        catch (Exception e) {
            ui.println(e.getMessage());
        }


        this.finalMinigame = new finalGuessingMinigame(world, ui);
        this.cmdHandler = new CommandHandler(finalMinigame);
    }


    public void play() {
        try {
            uvod();

            //Zavolání např. nějaké fileWrite metody
            while (!cmdHandler.isAboutToExit()) {
                ui.println("Nacházíte se v místnosti: " + font.lightBlue() + world.getPlayerRoom().getName() + font.reset());
                ui.println("Můžete se posunout do místností: " + world.getPlayerRoom().availableRoomNamesText());

                if (player.getInventory() != null) {
                    ui.println("V inventáři máte item: " + font.yellow() + player.getInventory().getName() + font.reset());
                }


                ui.print("\nZadejte příkaz >>");
                String userCommand = ui.scanNextLine();

                ui.println(">> " + cmdHandler.fetchDecodeExecuteCommand(userCommand, world, player));
                ui.print("\n");
            }
        }
        catch (Exception e) {
            ui.println(e.getMessage());
        }
    }


    private void uvod() {
        if (!world.getCharacters().isEmpty()) {
            //Postava na první pozici v ArrayListu (podle JSON struktury) se úvodně "seznámí" s hráčem jako první a použije se jeden z jejich monologů jako právě začáteční k uvedení hráče do děje a tím se hned i použije a progresuje se na další, použije se jen pokud jsou alespoň 2 monology u této postavy, protože pak by s touto postavou hráč nemohl mluvit znovu.
            if (world.getCharacters().getFirst().getMonologue().size() > 1) {
                ui.println(cmdHandler.fetchDecodeExecuteCommand("Mluv " + world.getCharacters().getFirst().getName(), world, player));
                world.getCharacters().getFirst().progressMonologue();
                ui.println("\n");
            }
        }
    }
}