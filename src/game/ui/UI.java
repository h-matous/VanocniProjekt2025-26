package game.ui;

import java.text.Normalizer;

public interface UI {
    void print(String str);
    void println(String str);

    String scanNextLine();
    void resetLastString();
    String getLastString();

    void start();

    /**
     * Slouží k převodu vstupního řetězce do lépe zpracovatelného stavu (odstraní diakritiku a převede do malých písmen)
     * @param text řetězec, který bude převeden do bezdiakritického malého stavu
     * @return vrací zpracovaný řetězec
     */
    static String toLowercaseAscii(String text) {
        //https://stackoverflow.com/questions/3322152/is-there-a-way-to-get-rid-of-accents-and-convert-a-whole-string-to-regular-lette
        String output = Normalizer.normalize(text, Normalizer.Form.NFD);
        output = output.replaceAll("\\p{M}", "");

        output = output.toLowerCase();

        return output;

    }
}
