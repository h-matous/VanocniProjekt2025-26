package game.command;

import game.GameData;
import game.Player;


/**
 * Abstraktní třída Command představuje „template“ jak mají třídy všech odděděných příkazů vypadat (mají obsahovat metody execute() a exit())
 */
public abstract class Command {
    protected String command;

    /**
     * Metoda k nastavení názvu příkazu
     * @param command String název příkazu
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Metoda execute() slouží k spuštění určitého příkazu
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    public abstract String execute(String param, GameData world, Player player);

    /**
     * Metoda exit() zjišťuje, jestli po zavolání určitého příkazu má hra zrovna skončit
     * @return boolean jestli má hra po tomto příkazu skončit
     */
    public abstract boolean exit();
}
