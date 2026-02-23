package game.command;

import java.util.HashMap;

import game.GameData;
import game.Player;
import game.command.commands.*;

import game.ui.UI;
import game.ui.font;

/**
 * Třída CommandHandler slouží k zpracování a následně spuštění příkazu získaného od uživatele
 */
public class CommandHandler {
    //HashMapa, která přiřazuje String příkazu k instanci příkazu Command, který obsahuje metodu execute
    private HashMap<String, Command> commandMap;

    private finalGuessingMinigame minigame;

    //Uchovává stav, který říká jestli má hra právě skončit
    private boolean exit;


    /**
     * Konstruktor k vytvoření instance HashMapy a její inicializaci
     */
    public CommandHandler(finalGuessingMinigame minigame) {
        commandMap = new HashMap<>();
        this.minigame = minigame;
        exit = false;

        initializeCommands();
    }


    /**
     * Slouží k inicializaci všech příkazů Command a přidání do HashMapy
     */
    public void initializeCommands() {
        commandMap.put("jdi", new Jdi());
        commandMap.put("konec", new Konec());
        commandMap.put("pomoc", new Pomoc());
        commandMap.put("napoveda", new Napoveda());
        commandMap.put("vezmi", new Vezmi());
        commandMap.put("poloz", new Poloz());
        commandMap.put("pouzij", new Pouzij());
        commandMap.put("mluv", new Mluv());
        commandMap.put("prozkoumat", new Prozkoumat());
        commandMap.put("zkombinovat", new Zkombinovat());
    }

    /**
     * Slouží k získání jestli Hra zrovna končí
     * @return vrací boolean pokud má Hra skončit
     */
    public boolean isAboutToExit() {
        return exit;
    }


    /**
     * Slouží k zpracování příkazu získaného od uživatele a násladně jeho spuštění
     * @param userCommand textový řetězec, získaný jako příkaz od uživatele
     * @param world instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se poté následně ukáže uživateli
     */
    public String fetchDecodeExecuteCommand(String userCommand, GameData world, Player player) {
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
            toReturn = commandMap.get(command).execute(param, world, player);
            //Potencinální spuštění finální hádací minihry
            if (world.shouldPlayFinalGuessingMinigame()) minigame.play();
            exit = commandMap.get(command).exit();
        }
        else {
            toReturn = "Tento příkaz není definován! " + font.bold() + "Zkuste použít příkaz \"pomoc\", pokud si nevíte rady..." + font.reset();
        }

        return toReturn;
    }

}
