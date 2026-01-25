package game;


import game.command.CommandHandler;
import game.spaceship.SpaceshipMap;
import game.ui.ConsoleUI;
import game.ui.UI;

import game.ui.Loader;

public class Game {
    private UI ui;
    private SpaceshipMap map;
    private Player player;

    private CommandHandler cmdHandler;

    private GameData world;


    private final String gameDataResourcePath;


    public Game() {
        this.ui = new ConsoleUI();
        this.map = new SpaceshipMap();
        this.player = new Player();

        this.cmdHandler = new CommandHandler();

        gameDataResourcePath = "resource/gamedata.json";

        try {
            world = Loader.loadGameData(gameDataResourcePath);
        }
        catch (Exception e) {
            ui.println(e.getMessage());
        }
    }


    public void play() {
        try {
            //Zavolání např. nějaké fileWrite metody

            while (!cmdHandler.isAboutToExit()) {
                ui.print("\nZadejte příkaz >>");
                String userCommand = ui.scanNextLine();

                ui.println(">> " + cmdHandler.fetchDecodeExecuteCommand(userCommand));
            }
        }
        catch (Exception e) {
            ui.println(e.getMessage());
        }
    }


}


//TODO: Všechno