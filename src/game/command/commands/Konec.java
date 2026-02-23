package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.ui.font;

/**
 * Třída Konec reprezentuje Command, který umožňuje Hráči ukončit hru předčasně
 */
public class Konec extends Command {
    /**
     * Metoda execute u příkazu Konec ukoční předčasně hru
     * @param param String parametr, který byl uživatelem specifikován po příkazu
     * @param world instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        return font.bold() + font.blue() + "Ukončování programu..." + font.reset();
    }

    /**
     * Metoda exit() zjišťuje, jestli po zavolání tohoto příkazu má hra zrovna skončit, v tomto případě vždy má
     * @return boolean jestli má hra po tomto příkazu skončit (true)
     */
    @Override
    public boolean exit() {
        return true;
    }
}
