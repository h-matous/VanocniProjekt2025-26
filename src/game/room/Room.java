package game.room;

import game.ui.UI;
import game.ui.font;

import java.util.ArrayList;


public class Room {
    private String name;

    private ArrayList<String> availableRoomNames;

    public Room() {}

    public Room(String name) {
        this.name = name;
    }

    public Room(String name, ArrayList<String> availableRoomNames) {
        this.name = name;
        this.availableRoomNames = availableRoomNames;
    }

    public String getName() {
        return name;
    }

    public boolean isNextToRoom(Room room) {
        for (int i = 0; i < availableRoomNames.size(); i++) {
            if (UI.toLowercaseAscii(availableRoomNames.get(i)).equals(UI.toLowercaseAscii(room.getName()).trim())) {
                return true;
            }
        }

        return false;
    }


    public String availableRoomNamesText() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < availableRoomNames.size(); i++) {

            toReturn.append(font.green());
            toReturn.append(availableRoomNames.get(i));
            toReturn.append(font.reset());

            if (i < availableRoomNames.size() - 1) {
                toReturn.append(", ");
            }
        }

        return toReturn.toString();
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
