package game;

import game.spaceship.SpaceshipMap;
import game.ui.ConsoleUI;
import game.ui.UI;

public class Game {
    private UI ui;
    private SpaceshipMap map;
    private Player player;


    public Game() {
        this.ui = new ConsoleUI();
        this.map = new SpaceshipMap();
        this.player = new Player();
    }

    public void play() {

    }

}


//TODO: Všechno