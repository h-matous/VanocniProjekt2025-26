package game.ui;

/**
 * Třída font "písmo" slouží k změně barev nebo jiných vlastností písma pomocí ANSI escapové sekvence
 */
public class font {
    //Modifikace

    /**
     * Ztučenění písma
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String bold() {
        return "\033[1m";
    }

    /**
     * Naklonění písma
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String italic() {
        return "\033[3m";
    }

    /**
     * Podtržení písma
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String underline() {
        return "\033[4m";
    }

    /**
     * Přeškrtnutí písma
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String strikethrough() {
        return "\033[9m";
    }


    //Barvy

    /**
     * Červené písmo
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String red() {
        return "\033[31m";
    }

    /**
     * Zelené písmo
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String green() {
        return "\033[32m";
    }

    /**
     * Žluté písmo
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String yellow() {
        return "\033[33m";
    }

    /**
     * Modré písmo
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String blue() {
        return "\033[34m";
    }

    /**
     * Purpurová barva
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String magenta() {
        return "\033[35m";
    }

    /**
     * Azurová barva
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String cyan() {
        return "\033[36m";
    }



    //Vlastní barvy

    /**
     * Slouží k tvorbě vlastní barvy pomocí intenzit r, g, b (0-255 pro každou intenzitu, 256³ = 16777216 kombinací)
     * @param r intenzita červené barvy
     * @param g intenzita zelené barvy
     * @param b intenzita modré barvy
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String rgb(int r, int g, int b) {
        return ("\033[38;2;" + r + ";" + g + ";" + b + "m");
    }

    /**
     * Vlastní oranžová barva
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String orange() {
        return rgb(250, 140, 25);
    }

    /**
     * Vlastní růžová barva
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String pink() {
        return rgb(250, 80, 225);
    }

    /**
     * Vlastní světle modrá barva
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String lightBlue() {
        return rgb(90, 160, 235);
    }

    /**
     * Vlastní světle červená barva
     * @return vrací řetězec (String), který mění vzhled písma
     */
    public static String lightRed() {
        return rgb(250, 80, 80);
    }

    //Reset

    /**
     * Resetování všech použitých vlastností písma
     * @return vrací řetězec (String), který deaktivuje všechny předešlé vlastnosti a změní písmo do původního stavu
     */
    public static String reset() {
        return "\033[0m";
    }
}