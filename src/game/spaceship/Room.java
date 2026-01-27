package game.spaceship;

import game.ui.UI;

import java.util.ArrayList;


public class Room {
    private String name;

    private ArrayList<String> availableRoomNames;

    public Room() {}


    public String getName() {
        return name;
    }

    public boolean isNextToRoom(String roomName) {
        for (int i = 0; i < availableRoomNames.size(); i++) {
            if (UI.toLowercaseAscii(availableRoomNames.get(i)).equals(UI.toLowercaseAscii(roomName))) {
                return true;
            }
        }

        return false;
    }


    public String availableRoomNamesText() {
        String toReturn = "";
        for (int i = 0; i < availableRoomNames.size(); i++) {

            toReturn = toReturn + availableRoomNames.get(i);

            if (i < availableRoomNames.size() - 1) {
                toReturn = toReturn + ", ";
            }
        }

        return toReturn;
    }

    public void setAvailableRoomNames(ArrayList<String> availableRoomNames) {
        this.availableRoomNames = availableRoomNames;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAvailableRoomNames() {
        return availableRoomNames;
    }
}
