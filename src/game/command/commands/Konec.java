package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;


public class Konec extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        return "Ukončování programu...";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
