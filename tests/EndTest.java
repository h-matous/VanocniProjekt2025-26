import game.Game;
import game.GameData;
import game.command.commands.Konec;
import game.Player;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída HelpTest slouží k testování příkazu Napoveda
 */
public class EndTest {
    private Konec konec;

    private Player player;


    /**
     * Třída FakeWorld reprezentuje „pomyslný“ herní svět
     */
    private static class FakeWorld extends GameData {
    }

    private FakeWorld fakeWorld;

    /**
     * Metoda slouží k nastavení všech vlastností Testu
     */
    @BeforeEach
    void setUp() {
        fakeWorld = new FakeWorld();

        player = new Player();

        konec = new Konec();
    }

    /**
     * Metoda slouží k spuštění Testu
     */
    @Test
    void execute() {
        konec.execute("", fakeWorld, player);

        assertTrue(konec.exit());
    }
}
