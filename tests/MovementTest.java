import game.GameData;
import game.command.commands.Jdi;
import game.room.Room;
import game.Player;

import game.ui.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MovementTest {
    private Room room1;
    private Room room2;
    private Jdi jdi;

    private Player player;

    private static class FakeWorld extends GameData {
        private final HashMap<String, Room> map = new HashMap<>();

        void add(Room room) {
            map.put(UI.toLowercaseAscii(room.getName().trim()), room);
        }

        @Override
        public Room findRoom(String roomName) {
            Room room = map.get(UI.toLowercaseAscii(roomName));

            if (room == null) {
                throw new RuntimeException("Místnost s názvem: \"" + roomName + "\" nebyla nalezena!");
            }

            return room;
        }
    }

    private FakeWorld fakeWorld;

    @BeforeEach
    void setUp() {
        room1 = new Room("A", new ArrayList<>());
        room2 = new Room("B", new ArrayList<>());

        room1.getAvailableRoomNames().add("b");

        fakeWorld = new FakeWorld();
        fakeWorld.add(room1);
        fakeWorld.add(room2);

        fakeWorld.setPlayerRoom(room1);

        player = new Player();

        jdi = new Jdi();
    }

    @Test
    void execute() {
        jdi.execute("b", fakeWorld, player);
        assertEquals("B", fakeWorld.getPlayerRoomName());
    }
}
