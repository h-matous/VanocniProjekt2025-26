package game.command;

import game.GameData;
import game.Player;

public abstract class Command {
    protected String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public abstract String execute(String param, GameData world, Player player);

    public abstract boolean exit();
}
