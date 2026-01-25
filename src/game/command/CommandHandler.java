package game.command;

import java.util.HashMap;

import game.ui.UI;
import game.command.commands.Pomoc;

public class CommandHandler {
    private HashMap<String, Command> commandMap;
    private boolean exit;

    public CommandHandler() {
        commandMap = new HashMap<>();
        exit = false;

        initializeCommands();
    }

    //TODO: vyčíst všechny příkazy z HashMapy
    public void initializeCommands() {
        commandMap.put("pomoc", new Pomoc());
        //Všechny příkazy prostě blabla
    }

    public boolean isAboutToExit() {
        return exit;
    }

    //TODO: Přidat "param" do execute v Command

    public String fetchDecodeExecuteCommand(String userCommand) {

        //Fetch + Decode
        String command = UI.toLowercaseAscii(userCommand).split(" ")[0];
        String toReturn = "";

        //Execute
        if (commandMap.containsKey(command)) {
            toReturn = commandMap.get(command).execute();
            exit = commandMap.get(command).exit();
        }
        else {
            toReturn = "Tento příkaz není definován";
        }

        return toReturn;
    }

}
