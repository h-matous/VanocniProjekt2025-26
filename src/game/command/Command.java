package game.command;

import game.GameData;

public abstract class Command {
    protected String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public abstract String execute(String param, GameData world);

    public abstract boolean exit();
}
