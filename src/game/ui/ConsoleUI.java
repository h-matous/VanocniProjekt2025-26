package game.ui;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.GameData;
import game.command.Command;
import game.command.commands.Pomoc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    //TODO: vyčíst všechny příkazy z HashMapy
    public void initializeCommands() {
        commandMap.put("pomoc", new Pomoc());
        //Všechny příkazy prostě blabla
    }

    private void fetchDecodeExecuteCommand() {
        print("\nZadejte příkaz >>");
        scanNextLine();

        String command = UI.toLowercaseAscii(getLastString()).split(" ")[0];

        if (commandMap.containsKey(command)) {
            println(">> " + commandMap.get(command).execute());
            exit = commandMap.get(command).exit();
        }
        else {
            println(">> Tento příkaz není definován");
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



    //TODO: object mapper loadGameData from json with jackson
    @Override
    public GameData loadGameData() {
        ObjectMapper parser = new ObjectMapper();

        String resourcePath = "resource/gamedata.json";

        try {
            InputStream input = new FileInputStream(resourcePath);

            return parser.readValue(input, GameData.class);
        }
        catch (FileNotFoundException e) {
            println("Soubor k načtení světa \"resource/gamedata.json\" nebyl nalezen! Nelze spustit hru!");
        }
        catch (Exception e) {
            println("Nelze načíst herní svět!");
        }

        return null;
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
