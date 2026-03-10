import game.Game;
import game.GameData;
import game.command.commands.Napoveda;
import game.Player;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída HelpTest slouží k testování příkazu Napoveda
 */
public class HelpTest {
    private Napoveda napoveda;

    private Player player;


    /**
     * Třída FakeWorld reprezentuje „pomyslný“ herní svět
     */
    private static class FakeWorld extends GameData {
        /**
         * Slouží k získání nápovědy, kterou si hráč vyžádá, když neví jak pokračovat
         * @return vrací nápovědu jako String
         */
        @Override
        public String getHint() {
            return "Nápověda pro hráče...";
        }
    }

    private FakeWorld fakeWorld;

    /**
     * Metoda slouží k nastavení všech vlastností Testu
     */
    @BeforeEach
    void setUp() {
        fakeWorld = new FakeWorld();

        player = new Player();

        napoveda = new Napoveda();
    }

    /**
     * Metoda slouží k spuštění Testu
     */
    @Test
    void execute() {
        String fetchedHint = napoveda.execute("", fakeWorld, player);
        assertEquals(fakeWorld.getHint(), fetchedHint);
    }
}
