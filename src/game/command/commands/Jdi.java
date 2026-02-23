package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.room.Room;

/**
 * Třída Jdi reprezentuje Command, který umožňuje Hráči posouvat se po mapě
 */
public class Jdi extends Command {
    /**
     * Metoda execute u příkazu Jdi kontroluje jestli se Hráč může do této místnosti posunout a následně změní jeho pozici na novou
     * @param param String parametr, který byl uživatelem specifikován po příkazu
     * @param world instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        String locationName = param.split(" ")[0].trim();

        Room roomToMoveTo;

        try {
            roomToMoveTo = world.findRoom(locationName);
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (world.playerNextToRoom(roomToMoveTo)) {
            world.setPlayerRoom(roomToMoveTo);

            return "Přicházíte do místnosti: " + world.getPlayerRoom().getName();
        }

        return "Nelze se odsud přímo posunout do této místnosti!";
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
