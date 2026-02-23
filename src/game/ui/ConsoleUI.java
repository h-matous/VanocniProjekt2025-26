package game.ui;

import java.util.Scanner;

/**
 * Třída ConsoleUI implementuje „vzorové“ uživatelské rozhraní UI, slouží k interakci s uživatelem skrze příkazový řádek
 */
public class ConsoleUI implements UI {
    private Scanner scn;

    private String lastStringScan;

    /**
     * Konstruktor inicializuje Scanner
     */
    public ConsoleUI() {
        scn = new Scanner(System.in);

        resetLastString();
    }


    /**
     * Slouží k vypsání Stringu do konzole
     * @param str textový řetězec String, který se má vypsat
     */
    @Override
    public void print(String str) {
        System.out.print(str);
    }

    /**
     * Slouží k vypsání Stringu do konzole s novým řádkem
     * @param str textový řetězec String, který se má vypsat
     */
    @Override
    public void println(String str) {
        print(str + "\n");
    }


    /**
     * Slouží k zjištění inputu od uživatele z konzole
     * @return vrací String, který uživatel napsal na řádek
     */
    @Override
    public String scanNextLine() {
        lastStringScan = scn.nextLine();
        return lastStringScan;
    }

    /**
     * Slouží k resetování poslední načtené hodnoty
     */
    @Override
    public void resetLastString() {
        lastStringScan = "";
    }

    /**
     * Slouží k získání poslední načtené hodnoty od uživatele z konzole
     * @return vrací textový řetězec String posledního načteného řádku od uživatele
     */
    @Override
    public String getLastString() {
        return lastStringScan;
    }
}
