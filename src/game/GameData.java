package game;

import game.item.Item;
import game.character.Character;
import game.spaceship.Room;

import java.util.ArrayList;

public class GameData {
    //items/objects, characters, locations/rooms, quests
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private ArrayList<Room> rooms;

    /**
     * Empty constructor for loading GameData with jackson
     */
    public GameData() {}

    /**
     * Finds a specific room by its name.
     * @param name the name of the room to be found
     * @return the matching room
     */
    public Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }

        throw new IllegalArgumentException("Neexistuje lokace s jménem: " + name);
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "items=" + items +
                ", characters=" + characters +
                ", rooms=" + rooms +
                '}';
    }
}
