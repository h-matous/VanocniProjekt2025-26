package game.command;

public abstract class Command {
    protected String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public abstract String execute();

    public abstract boolean exit();
}
