package game.command.commands;

import game.command.Command;

public class Napoveda extends Command {
    @Override
    public String execute() {
        return "Musíš vyhrát - nápověda";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
