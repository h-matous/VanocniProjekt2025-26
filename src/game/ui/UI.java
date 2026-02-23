package game.ui;

import java.text.Normalizer;

/**
 * Rohraní UI (Uživatelské Rozhraní) slouží k uchování metod, které se u implementovaných tříd mají vyskytovat
 */
public interface UI {
    /**
     * Slouží k výpisu textového řetězce uživateli
     * @param str textový řetězec String, který se má vypsat
     */
    void print(String str);
    /**
     * Slouží k výpisu textového řetězce uživateli s novým řádkem
     * @param str textový řetězec String, který se má vypsat
     */
    void println(String str);

    /**
     * Slouží k zjištění inputu od uživatele
     * @return vrací String, který uživatel napsal na řádek
     */
    String scanNextLine();

    /**
     * Slouží k resetování poslední načtené hodnoty
     */
    void resetLastString();

    /**
     * Slouží k získání poslední načtené hodnoty od uživatele
     * @return vrací textový řetězec String posledního načteného řádku od uživatele
     */
    String getLastString();

    /**
     * Slouží k převodu vstupního řetězce do lépe zpracovatelného stavu (odstraní diakritiku a převede do malých písmen)
     * @param text řetězec, který bude převeden do bezdiakritického malého stavu
     * @return vrací zpracovaný textový řetězec String
     */
    static String toLowercaseAscii(String text) {
        //https://stackoverflow.com/questions/3322152/is-there-a-way-to-get-rid-of-accents-and-convert-a-whole-string-to-regular-lette
        String output = Normalizer.normalize(text, Normalizer.Form.NFD);
        output = output.replaceAll("\\p{M}", "");

        output = output.toLowerCase();

        return output;

    }
}
