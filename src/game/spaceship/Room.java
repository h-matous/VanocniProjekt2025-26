package game.spaceship;

import game.character.Character;
import game.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;

    private ArrayList<Item> items; //objects
    private Character character;
    private ArrayList<Room> availableRooms;


    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addAvailableRoom(Room room) {
        this.availableRooms.add(room);
    }


    public Room(String name) {
        this.name = name;

        this.items = new ArrayList<>();
        this.character = null;
        this.availableRooms = new ArrayList<>();
    }

    public Room(String name, Item[] items) {
        this.name = name;

        this.items = new ArrayList<>(List.of(items));
        this.character = null;
        this.availableRooms = new ArrayList<>();
    }

    public Room(String name, Character character) {
        this.name = name;

        this.items = new ArrayList<>();
        this.character = character;
        this.availableRooms = new ArrayList<>();
    }

    public Room(String name, Item[] items, Character character) {
        this.name = name;

        this.items = new ArrayList<>(List.of(items));
        this.character = character;
        this.availableRooms = new ArrayList<>();
    }
}
