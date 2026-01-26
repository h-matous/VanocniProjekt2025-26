package game;

import game.item.Item;
import game.character.Character;
import game.spaceship.Room;
import game.ui.UI;

import java.util.ArrayList;

public class GameData {
    //items/objects, characters, locations/rooms, quests
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private ArrayList<Room> rooms;

    private String playerRoomName;

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
            if (UI.toLowercaseAscii(room.getName()).equals(UI.toLowercaseAscii(name))) {
                return room;
            }
        }

        throw new IllegalArgumentException("Neexistuje lokace s jménem: " + name);
    }
    //TODO: vylepšit, přidat metodu jestli jsou 2 místnosti vedle sebe a pak použít v commands.Jdi u metody execute


    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setPlayerRoomName(String playerRoomName) {
        this.playerRoomName = playerRoomName;
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

    public String getPlayerRoomName() {
        return playerRoomName;
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
