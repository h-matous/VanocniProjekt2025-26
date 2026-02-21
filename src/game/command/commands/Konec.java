package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.ui.font;

public class Konec extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        return font.bold() + font.blue() + "Ukončování programu..." + font.reset();
    }

    @Override
    public boolean exit() {
        return true;
    }
}
