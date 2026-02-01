package game;

import game.item.Item;
import game.character.Character;
import game.room.Room;
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
     * @param roomName the name of the room to be found
     * @return the matching room
     */
    public Room findRoom(String roomName) {
        for (Room room : rooms) {
            if (UI.toLowercaseAscii(room.getName()).equals(UI.toLowercaseAscii(roomName))) {
                return room;
            }
        }

        throw new IllegalArgumentException("Neexistuje lokace s názvem: \"" + roomName + "\"");
    }
    //TODO: vylepšit, přidat metodu jestli jsou 2 místnosti vedle sebe a pak použít v commands.Jdi u metody execute


    public Character findCharacter(String characterName) {
        for (Character character : characters) {
            if (UI.toLowercaseAscii(character.getName()).equals(UI.toLowercaseAscii(characterName))) {
                return character;
            }
        }

        throw new IllegalArgumentException("Neexistuje postava s názvem: \"" + characterName + "\"");
    }


    public Item findItem(String itemName) {
        for (Item item : items) {
            if (UI.toLowercaseAscii(item.getName()).equals(UI.toLowercaseAscii(itemName))) {
                return item;
            }
        }

        throw new IllegalArgumentException("Neexistuje item s názvem: \"" + itemName + "\"");
    }



    public void setPlayerRoom(Room playerRoom) {
        this.playerRoomName = playerRoom.getName();
    }


    public boolean playerNextToRoom(Room room) {
        return getPlayerRoom().isNextToRoom(room);
    }

    public boolean playerInTheRoomAsCharacter(Character character) {
        return getPlayerRoom().equals(findRoom(character.getLocation()));
    }




    public Room getPlayerRoom() {
        return findRoom(getPlayerRoomName());
    }


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setPlayerRoomName(String playerRoomName) {
        this.playerRoomName = playerRoomName;
    }
    public String getPlayerRoomName() {
        return playerRoomName;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
