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

    private ArrayList<String> endingPhasesRequirementMessages;

    private String endingPhaseGuessingMinigameMessage;

    private String endingStory;

    //Nápověda
    private String hint;

    /**
     * Empty constructor for loading GameData with jackson
     */
    public GameData() {}

    public void playFinalGuessingMinigame() {
        
    }


    //Slouží k zjištění zdali hráč podle příběhu hry dokončil první úkol, díky kterému může postupovat ve hře a může vykonat další úkol
    public boolean isFirstProgressingPhaseDone() {
        //Podle navržení JSONu, je vyžadováno, aby byl 1. Item (Pojistka) umíštěn v 2. Lokaci/Místnosti (Chodba).
        //Navrženo podle systému indexování v ArrayListu, aby bylo možné upravovat příběh a určité Itemy se musely umístit do určitých místností podle gameDesignu.

        if (items.isEmpty()) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Itemů. Chybný JSON!");
        if (rooms.size() < 2) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Místností. Chybný JSON!");

        Item firstItemFromJSON = items.getFirst();
        Room firstItemLocation = findRoom(firstItemFromJSON.getLocation());
        Room secondRoomFromJSON = rooms.get(1);

        //Pokud je ten Item v požadované Místnosti
        return firstItemLocation.equals(secondRoomFromJSON);
    }

    //Slouží k zjištění zdali hráč podle příběhu hry dokončil druhý úkol, díky kterému může poté navázat na poslední úkol
    public boolean isSecondProgressingPhaseDone() {
        //Podle navržení JSONu, je vyžadováno, aby byl poslední Item (Baterie) umíštěn v 2. Lokaci/Místnosti (Strojovna).
        //Navrženo podle systému indexování v ArrayListu, aby bylo možné upravovat příběh a určité Itemy se musely umístit do určitých místností podle gameDesignu.

        if (items.isEmpty()) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Itemů. Chybný JSON!");
        if (rooms.size() < 2) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Místností. Chybný JSON!");

        Item lastItemFromJSON = items.getLast();
        Room lastItemLocation = findRoom(lastItemFromJSON.getLocation());
        Room lastRoomFromJSON = rooms.getLast();

        //Pokud je ten Item v požadované Místnosti a zároveň pokud byla dokončená první fáze (což jakože byla, ale pro jistotu ;))
        return lastItemLocation.equals(lastRoomFromJSON) && isFirstProgressingPhaseDone();
    }


    public String getStringOfItemsText(ArrayList<Item> items) {
        StringBuilder toReturn = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            toReturn.append(items.get(i).getName());

            if (i < items.size() - 1) {
                toReturn.append(", ");
            }
        }

        return toReturn.toString();
    }

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

        throw new IllegalArgumentException("Neexistuje lokace s názvem: \"" + roomName + "\"!");
    }

    public Item getItemFromParam(String param) {
        String itemName = param.split(" ")[0].trim();

        return findItem(itemName);
    }

    public Room getItemLocationFromItem(Item item) {
        return findRoom(item.getLocation());
    }


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
        try {
            return findRoom(getPlayerRoomName());
        }
        catch (IllegalArgumentException e) {
            return new Room("Nelze načíst lokaci hráče! Špatný formát JSON souboru!");
        }
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

    public ArrayList<String> getEndingPhasesRequirementMessages() {
        return endingPhasesRequirementMessages;
    }

    public void setEndingPhasesRequirementMessages(ArrayList<String> endingPhasesRequirementMessages) {
        this.endingPhasesRequirementMessages = endingPhasesRequirementMessages;
    }

    public String getEndingPhaseGuessingMinigameMessage() {
        return endingPhaseGuessingMinigameMessage;
    }

    public void setEndingPhaseGuessingMinigameMessage(String endingPhaseGuessingMinigameMessage) {
        this.endingPhaseGuessingMinigameMessage = endingPhaseGuessingMinigameMessage;
    }

    public String getEndingStory() {
        return endingStory;
    }

    public void setEndingStory(String endingStory) {
        this.endingStory = endingStory;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
