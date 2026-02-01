package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

public class Vezmi extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
