package game.command;

import java.util.HashMap;

import game.GameData;
import game.command.commands.Jdi;
import game.command.commands.Konec;
import game.command.commands.Pomoc;

import game.command.commands.Prozkoumat;
import game.ui.UI;

public class CommandHandler {
    private HashMap<String, Command> commandMap;
    private boolean exit;

    public CommandHandler() {
        commandMap = new HashMap<>();
        exit = false;

        initializeCommands();
    }


    public void initializeCommands() {
        commandMap.put("jdi", new Jdi());
        commandMap.put("konec", new Konec());
        commandMap.put("pomoc", new Pomoc());
        commandMap.put("napoveda", new Pomoc());
        commandMap.put("vezmi", new Pomoc());
        commandMap.put("poloz", new Pomoc());
        commandMap.put("pouzij", new Pomoc());
        commandMap.put("mluv", new Pomoc());
        commandMap.put("prozkoumat", new Prozkoumat());
        commandMap.put("zkombinovat", new Prozkoumat());
    }

    public boolean isAboutToExit() {
        return exit;
    }



    public String fetchDecodeExecuteCommand(String userCommand, GameData world) {

        //Fetch + Decode
        String command = UI.toLowercaseAscii(userCommand).split(" ")[0];
        String param = "";

        //Vyskytuje se v podmínce +1, protože mezi commandem a parametrem se nachází právě jedna mezera
        if (userCommand.length() > command.length() + 1) {
            //Zde je +1, aby se v parametru nevyskytoval ještě poslední písmeno z příkazu
            param = UI.toLowercaseAscii(userCommand).substring(command.length() + 1);

            //Odstranění všech mezer mezi příkazem a parametry
            while (param.charAt(0) == ' ') {
                param = param.substring(1);
            }
        }

        String toReturn = "";

        //Execute
        if (commandMap.containsKey(command)) {
            toReturn = commandMap.get(command).execute(param, world);
            exit = commandMap.get(command).exit();
        }
        else {
            toReturn = "Tento příkaz není definován!";
        }

        return toReturn;
    }

}
