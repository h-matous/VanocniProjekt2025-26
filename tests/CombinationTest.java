import game.Game;
import game.GameData;
import game.command.commands.Zkombinovat;
import game.item.Item;
import game.room.Room;
import game.Player;

import game.ui.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída CombinationTest slouží k testování příkazu Zkombinovat
 */
public class CombinationTest {
    private Room room1;
    private Room room2;

    private Item item1;
    private Item item2;
    private Item item3;
    private Zkombinovat zkombinovat;

    private Player player;


    /**
     * Třída FakeWorld reprezentuje „pomyslný“ herní svět
     */
    private static class FakeWorld extends GameData {
        private final ArrayList<Room> rooms = new ArrayList<>();
        private final ArrayList<Item> items = new ArrayList<>();

        //Název místnosti ve které se právě Hráč nachází
        private String playerRoomName;

        /**
         * Slouží k přidání Místnosti do ArrayListu
         * @param room Místnost, která se přidá
         */
        void addRoom(Room room) {
            rooms.add(room);
        }

        /**
         * Slouží k přidání Itemu do ArrayListu
         * @param item Item, který se přidá
         */
        void addItem(Item item) {
            items.add(item);
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
         * Nastaví hráčovu Místnost, kde se právě nachází
         * @param playerRoom hráčova Místnost
         */
        @Override
        public void setPlayerRoom(Room playerRoom) {
            this.playerRoomName = playerRoom.getName();
        }

        /**
         * Získá název Místnosti ve které se Hráč zrovna nachází
         * @return vrací název konkrétní místnosti
         */
        @Override
        public String getPlayerRoomName() {
            return playerRoomName;
        }

        /**
         * Slouží k zjištění zdali hráč podle příběhu hry dokončil první úkol, díky kterému může postupovat ve hře a může vykonat další úkol (kombinace Itemů)
         * @return vrací boolean jestli tuto progresovou fázi Hráč dokončil
         */
        @Override
        public boolean isFirstProgressingPhaseDone() {
            return true; //Musí být splněn 1 herní požadavek, aby se dala použít kombinace (např. v Kuchyň musí být odemčená, aby jsme se do ni dostali nebo musí mít přístup k elektřině, aby se dalo péct)
        }

        /**
         * Slouží k získání všech Itemů v „pomyslném“ Herním světě
         * @return vrací ArrayList všech Itemů
         */
        @Override
        public ArrayList<Item> getItems() {
            return items;
        }

        /**
         * Slouží k získání všech Místností v „pomyslném“ Herním světě
         * @return vrací ArrayList všech Místností
         */
        @Override
        public ArrayList<Room> getRooms() {
            return rooms;
        }

    }

    private FakeWorld fakeWorld;

    /**
     * Metoda slouží k nastavení všech vlastností Testu
     */
    @BeforeEach
    void setUp() {
        room1 = new Room("Kuchyň", new ArrayList<>()); //Předposlední Místnost představuje Místnost, kde se dají Itemy kombinovat (vychází z JSON struktury)
        room2 = new Room("Obývák", new ArrayList<>()); //Musí existovat alespoň 2 Místnosti, aby existovala předposlední Místnost

        item1 = new Item("Mouka", room1.getName(), true, false, true);
        item2 = new Item("Voda", room1.getName(), true, false, true);
        item3 = new Item("Těsto", "", true, false, false); //Výsledek kombinace

        fakeWorld = new FakeWorld();
        fakeWorld.addRoom(room1);
        fakeWorld.addRoom(room2);

        fakeWorld.addItem(item1);
        fakeWorld.addItem(item2);
        fakeWorld.addItem(item3);

        player = new Player();

        fakeWorld.setPlayerRoom(room1); //Hráč musí být v kombinační místnosti, aby šlo kombinovat


        zkombinovat = new Zkombinovat();
    }

    /**
     * Metoda slouží k spuštění Testu
     */
    @Test
    void execute() {
        zkombinovat.execute("", fakeWorld, player);
        assertEquals(room1.getName(), item3.getLocation()); //Jestli se item3 (Těsto) nachází v room1 (Kuchyň) po tom, co se provedla kombinace
    }
}
