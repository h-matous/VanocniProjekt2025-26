package game.ui;

import game.command.Command;
import game.command.commands.Pomoc;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI implements UI {
    private Scanner scn;

    private String lastStringScan;

    public ConsoleUI() {
        scn = new Scanner(System.in);

        resetLastString();
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
