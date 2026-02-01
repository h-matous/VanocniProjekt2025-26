package game;

import game.item.Item;

public class Player {
    private Item inventory;

    public Player() {
        setInventory(null);
    }

    public void setInventory(Item inventory) {
        this.inventory = inventory;
    }

    public Item getInventory() {
        return inventory;
    }
}
