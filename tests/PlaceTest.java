import game.GameData;
import game.command.commands.Poloz;
import game.item.Item;
import game.room.Room;
import game.Player;

import game.ui.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída PlaceTest slouží k testování příkazu Poloz
 */
public class PlaceTest {
    private Room room;
    private Item item;
    private Poloz poloz;

    private Player player;


    /**
     * Třída FakeWorld reprezentuje „pomyslný“ herní svět
     */
    private static class FakeWorld extends GameData {
        private ArrayList<Room> rooms = new ArrayList<>();
        private ArrayList<Item> items = new ArrayList<>();


        /**
         * Slouží k přidání Místnosti do ArrayListu
         * @param room Místnost, která se přidá
         */
        void addRoom(Room room) {
            rooms.add(room);
        }

        /**
         * Slouží k získání všech Místností v Herním světě
         * @return vrací ArrayList Místností
         */
        @Override
        public ArrayList<Room> getRooms() {
            return rooms;
        }


        /**
         * Najde specifickou Místnost podle její jména
         * @param roomName název Místnosti k nalezení
         * @return shodující Místnost
         */
        @Override
        public Room findRoom(String roomName) {
            for (Room room : rooms) {
                if (UI.toLowercaseAscii(room.getName()).equals(UI.toLowercaseAscii(roomName))) {
                    return room;
                }
            }

            throw new IllegalArgumentException("Neexistuje lokace s názvem: \"" + roomName + "\"!");
        }

        /**
         * Slouží k získání Hráčovy Místnosti, kde se nachází
         * @return vrací Místnost ve které se Hráč nachází
         */
        @Override
        public Room getPlayerRoom() {
            try {
                return findRoom(getPlayerRoomName());
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Nelze načíst lokaci hráče! Špatný formát JSON souboru!");
            }
        }

        /**
         * Najde specifický Item podle jeho názvu
         * @param itemName název hledaného Itemu
         * @return Item se shodujícím názvem
         */
        @Override
        public Item findItem(String itemName) {
            for (Item item : items) {
                if (UI.toLowercaseAscii(item.getName()).equals(UI.toLowercaseAscii(itemName))) {
                    return item;
                }
            }

            throw new IllegalArgumentException("Neexistuje item s názvem: \"" + itemName + "\"");
        }
    }

    private FakeWorld fakeWorld;

    /**
     * Metoda slouží k nastavení všech vlastností Testu
     */
    @BeforeEach
    void setUp() {
        room = new Room("mistnost", new ArrayList<>());
        item = new Item("item", room.getName(), true, false, false);

        fakeWorld = new FakeWorld();
        fakeWorld.addRoom(room);

        item.setLocation(room.getName());
        fakeWorld.setPlayerRoom(room);

        player = new Player();
        player.setInventory(item);

        poloz = new Poloz();
    }

    /**
     * Metoda slouží k spuštění Testu
     */
    @Test
    void execute() {
        poloz.execute("", fakeWorld, player);
        assertEquals(fakeWorld.findRoom(item.getLocation()), fakeWorld.getPlayerRoom()); //Jestlí se Položený Item nachází v Místnosti, kde je hráč
        //assertNull(player.getInventory()); //Jestli se Item nenachází v inventáři Hráče
    }
}
