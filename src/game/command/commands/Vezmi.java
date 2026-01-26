package game.command.commands;

import game.command.Command;

import game.GameData;

public class Vezmi extends Command {
    @Override
    public String execute(String param, GameData world) {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
