package game;

import game.item.Item;

import java.io.Serializable;

/**
 * Třída Player reprezentuje Hráče, který má inventář
 */
public class Player implements Serializable {
    private Item inventory;

    /**
     * Prázdný konstruktor
     */
    public Player() {}

    /**
     * Slouží k nastavení inventáře na nový Item
     * @param inventory Item k nastavení
     */
    public void setInventory(Item inventory) {
        this.inventory = inventory;
    }

    /**
     * Slouží k získání Itemu, který má právě Hráč v inventáři
     * @return vrací Item, který má Hráč v inventáři
     */
    public Item getInventory() {
        return inventory;
    }
}
