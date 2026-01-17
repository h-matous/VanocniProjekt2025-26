package game;

import game.item.Item;
import game.spaceship.Room;
import game.spaceship.SpaceshipMap;

public class Player {
    private Item inventory;
    private Room currentLocation;

    public Player() {
        this.inventory = null;
        this.currentLocation = SpaceshipMap.getRoom("Kokpit");
    }
}
