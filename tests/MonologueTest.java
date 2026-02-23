import game.GameData;
import game.character.Character;
import game.command.commands.Mluv;
import game.room.Room;
import game.Player;

import game.ui.UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída MonologueTest slouží k testování příkazu Mluv
 */
public class MonologueTest {
    private Room room;
    private Character postava;
    private Mluv mluv;

    private Player player;


    /**
     * Třída FakeWorld reprezentuje „pomyslný“ herní svět
     */
    private static class FakeWorld extends GameData {
        private ArrayList<Room> rooms = new ArrayList<>();
        private ArrayList<Character> characters = new ArrayList<>();


        /**
         * Slouží k přidání Místnosti do ArrayListu
         * @param room Místnost, která se přidá
         */
        void addRoom(Room room) {
            rooms.add(room);
        }

        /**
         * Slouží k přidání Postav do
         * @param character Postava, která se přidá
         */
        void addCharacter(Character character) {
            characters.add(character);
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
         * Najde postavu podle jejího jména
         * @param characterName jméno postavy
         * @return Postavu Character se shodujícím jménem
         */
        @Override
        public Character findCharacter(String characterName) {
            for (Character character : characters) {
                if (UI.toLowercaseAscii(character.getName()).equals(UI.toLowercaseAscii(characterName))) {
                    return character;
                }
            }

            throw new IllegalArgumentException("Neexistuje postava s názvem: \"" + characterName + "\"");
        }

    }

    private FakeWorld fakeWorld;

    /**
     * Metoda slouží k nastavení všech vlastností Testu
     */
    @BeforeEach
    void setUp() {
        room = new Room("mistnost", new ArrayList<>());
        postava = new Character("postava1", room.getName(), new ArrayList<>());
        postava.getMonologue().add("text");

        fakeWorld = new FakeWorld();
        fakeWorld.addRoom(room);

        fakeWorld.addCharacter(postava);

        fakeWorld.setPlayerRoom(room);

        player = new Player();

        mluv = new Mluv();
    }

    /**
     * Metoda slouží k spuštění Testu
     */
    @Test
    void execute() {
        String spokenText = mluv.execute("postava1", fakeWorld, player);
        assertEquals("postava1: text", spokenText);
    }
}
