package game;

import game.item.Item;

/**
 * Třída Player reprezentuje Hráče, obsahuje inventář
 */
public class Player {
    private Item inventory;

    /**
     * Konstruktor nastaví inventář na prázdný
     */
    public Player() {
        setInventory(null);
    }

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
