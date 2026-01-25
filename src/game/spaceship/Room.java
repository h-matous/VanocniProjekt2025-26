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
            if (UI.toLowercaseAscii(name).equals(UI.toLowercaseAscii(roomName))) {
                return true;
            }
        }

        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableRoomNames(ArrayList<String> availableRoomNames) {
        this.availableRoomNames = availableRoomNames;
    }

}
