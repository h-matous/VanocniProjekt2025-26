package game.command.commands;

import game.command.Command;

import game.GameData;

public class Napoveda extends Command {
    @Override
    public String execute(String param, GameData world) {
        return "Musíš vyhrát - nápověda";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
