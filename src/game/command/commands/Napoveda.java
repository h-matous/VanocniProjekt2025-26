package game.command.commands;


import game.command.Command;
import game.GameData;
import game.Player;



/**
 * Třída Napoveda reprezentuje Command, který umožňuje Hráči získat nápovědu, pokud si neví rady
 */
public class Napoveda extends Command {
    /**
     * Metoda execute u příkazu Napoveda vypíše Hráči pomocnou nápovědu
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        return world.getHint();
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
