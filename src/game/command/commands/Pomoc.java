package game.command.commands;

import game.command.Command;

public class Pomoc extends Command {

    @Override
    public String execute() {
        return "jdi <místnost>, konec, pomoc, napoveda, vezmi <předmět>, poloz, pouzij <předmět>, mluv <postava>, prozkoumat, zkombinovat";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
