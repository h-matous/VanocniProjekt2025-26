package game.spaceship;

import game.ui.UI;

import java.util.HashMap;

//TODO: smazat tuhle třídu
public class SpaceshipMap {
    static HashMap<String, Room> rooms;

    public SpaceshipMap() {
        rooms = new HashMap<>();
    }

    /*
    public void initializeRooms() {
        rooms.put("kokpit", new Room("Kokpit", new Character("Orion")));
        rooms.put("chodba", new Room("Chodba"));
        rooms.put("komunikace", new Room("Komunikace"));
        rooms.put("osetrovna", new Room("Ošetřovna"));
        rooms.put("kajuta", new Room("Kajuta", new Character("Xel")));
        rooms.put("hangar", new Room("Hangár", new Character("R5")));
        rooms.put("sklad", new Room("Sklad"));
        rooms.put("laborator", new Room("Laboratoř"));
        rooms.put("strojovna", new Room("Strojovna"));
    }
    */

    public static Room getRoom(String str) {
        String roomName = UI.toLowercaseAscii(str);

        if (rooms.containsKey(roomName)) {
            return rooms.get(roomName);
        }

        return null;
    }
}
