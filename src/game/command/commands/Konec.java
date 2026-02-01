package game.command.commands;

import game.command.Command;

import game.GameData;

public class Konec extends Command {
    @Override
    public String execute(String param, GameData world) {
        return "Ukončování programu...";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
