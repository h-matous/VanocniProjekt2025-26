package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

/**
 * Třída Pomoc reprezentuje Command, který Hráč může zavolat, když neví jaké příkazy má k dispozici
 */
public class Pomoc extends Command {
    /**
     * Metoda execute u příkazu Pomoc vypíše Hráči všechny dostupné příkazy
     * @param param String parametr, který byl uživatelem specifikován po příkazu
     * @param world instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        return "K dispozici jsou příkazy:\n" +
                "jdi <místnost>             : pohyb mezi propojenými místnostmi\n" +
                "konec                      : ukončení hry\n" +
                "pomoc                      : zobrazení dostupných příkazů\n" +
                "napoveda                   : nápověda pro postup ve hře\n" +
                "vezmi <předmět>            : vzít konkrétní předmět z místnosti\n" +
                "poloz                      : položit předmět z inventáře\n" +
                "pouzij <předmět>           : použít předmět v místnosti\n" +
                "mluv <postava>             : mluvit s konkrétní postavou\n" +
                "prozkoumat                 : zjištění informací o místnosti\n" +
                "zkombinovat                : zkombinování předmětů do jiného předmětu\n";
    }

    /**
     * Metoda exit() zjišťuje, jestli po zavolání tohoto příkazu má hra zrovna skončit, v tomto případě nemá
     * @return boolean jestli má hra po tomto příkazu skončit (false)
     */
    @Override
    public boolean exit() {
        return false;
    }
}
