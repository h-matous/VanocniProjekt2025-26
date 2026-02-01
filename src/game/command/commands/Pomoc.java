package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;


public class Pomoc extends Command {

    @Override
    public String execute(String param, GameData world, Player player) {
        return "jdi <místnost>, konec, pomoc, napoveda, vezmi <předmět>, poloz, pouzij <předmět>, mluv <postava>, prozkoumat, zkombinovat";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
