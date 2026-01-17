package game.ui;

import game.command.Command;
import game.command.commands.Pomoc;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI implements UI {
    private Scanner scn;
    private HashMap<String, Command> commandMap;
    private boolean exit;

    private String lastStringScan;

    public ConsoleUI() {
        scn = new Scanner(System.in);
        commandMap = new HashMap<>();
        exit = false;

        resetLastString();
    }

    public void initializeCommands() {
        commandMap.put("pomoc", new Pomoc());
        //Všechny příkazy prostě blabla
    }

    private void fetchDecodeExecuteCommand() {
        print("Zadejte příkaz >>");
        scanNextLine();

        String command = UI.toLowercaseAscii(getLastString()).split(" ")[0];

        if (commandMap.containsKey(command)) {
            print(">> " + commandMap.get(command).execute());
            exit = commandMap.get(command).exit();
        }
        else {
            print(">> Tento příkaz není definován");
        }

    }

    @Override
    public void start() {
        initializeCommands();

        try {
            //Zavolání např. nějaké fileWrite metody

            while (!exit) {
                fetchDecodeExecuteCommand();
            }
        }
        catch (Exception e) {
            println(e.getMessage());
        }
    }


    @Override
    public void print(String str) {
        System.out.print(str);
    }

    @Override
    public void println(String str) {
        print(str + "\n");
    }


    @Override
    public String scanNextLine() {
        lastStringScan = scn.nextLine();
        return lastStringScan;
    }

    @Override
    public void resetLastString() {
        lastStringScan = "";
    }

    @Override
    public String getLastString() {
        return lastStringScan;
    }
}
