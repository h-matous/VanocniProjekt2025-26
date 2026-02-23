import game.GameData;
import game.command.commands.Vezmi;
import game.item.Item;
import game.room.Room;
import game.Player;

import game.ui.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída PickupTest slouží k testování příkazu Vezmi
 */
public class PickupTest {
    private Room room;
    private Item item;
    private Vezmi vezmi;

    private Player player;


    /**
     * Třída FakeWorld reprezentuje „pomyslný“ herní svět
     */
    private static class FakeWorld extends GameData {
        private final HashMap<String, Room> map = new HashMap<>();
        private final ArrayList<Item> items = new ArrayList<>();

        /**
         * Slouží k přidání Místnosti do HashMapy
         * @param room Místnost, která se přidá
         */
        void addRoom(Room room) {
            map.put(UI.toLowercaseAscii(room.getName().trim()), room);
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
            Room room = map.get(UI.toLowercaseAscii(roomName));

            if (room == null) {
                throw new RuntimeException("Místnost s názvem: \"" + roomName + "\" nebyla nalezena!");
            }

            return room;
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
        fakeWorld.addItem(item);


        item.setLocation(room.getName());
        fakeWorld.setPlayerRoom(room);

        player = new Player();

        vezmi = new Vezmi();
    }

    /**
     * Metoda slouží k spuštění Testu
     */
    @Test
    void execute() {
        vezmi.execute(item.getName(), fakeWorld, player);
        assertEquals(item, player.getInventory()); //Jestlí má Hráč v inventáři tento Item
        //assertEquals("", item.getLocation()); //Jestli se Item nenachází na mapě
    }
}
