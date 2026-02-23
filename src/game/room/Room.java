package game.room;

import game.ui.UI;
import game.ui.font;

import java.util.ArrayList;

/**
 * Třída Room reprezentuje Místnost ve hře a na mapě
 */
public class Room {
    private String name;

    private ArrayList<String> availableRoomNames;

    public Room() {}

    /**
     * Kontruktor nastaví název Místnost
     * @param name String názvu Místnosti
     */
    public Room(String name) {
        this.name = name;
    }

    /**
     * Konstruktor nastaví název Místnosti a ArrayList názvů všech sousedících Místností
     * @param name String názvu Místnosti
     * @param availableRoomNames ArrayList Stringů s názvy sousedících Místností
     */
    public Room(String name, ArrayList<String> availableRoomNames) {
        this.name = name;
        this.availableRoomNames = availableRoomNames;
    }

    /**
     * Slouží k získání názvu Místnosti
     * @return vrací String názvu Místnosti
     */
    public String getName() {
        return name;
    }

    /**
     * Slouží k zištění jestli tato Místnost sousedí s Místností Room
     * @param room potencionálně sousedící Místnost Room
     * @return vrací boolean, jestli tyto Místnosti sousedí
     */
    public boolean isNextToRoom(Room room) {
        for (int i = 0; i < availableRoomNames.size(); i++) {
            if (UI.toLowercaseAscii(availableRoomNames.get(i)).equals(UI.toLowercaseAscii(room.getName()).trim())) {
                return true;
            }
        }

        return false;
    }


    /**
     * Slouží k získání textového výpisu všech názvů sousedících Mísností
     * @return vrací String obsahující výpis sousedících Místností
     */
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

    /**
     * Slouží k nastavení všech sousedících Místností
     * @param availableRoomNames ArrayList Stringů názvů všech těchto Místností
     */
    public void setAvailableRoomNames(ArrayList<String> availableRoomNames) {
        this.availableRoomNames = availableRoomNames;
    }

    /**
     * Slouží k nastavení názvu této Místnosti
     * @param name nový název Místnosti jako String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Slouží získání ArrayListu Stringů názvů všech sousedících Místností
     * @return vrací ArrayList Stringů názvu těchto Místností
     */
    public ArrayList<String> getAvailableRoomNames() {
        return availableRoomNames;
    }
}
