package game.ui;

//Třída game.ui.font "písmo" slouží k změně barev nebo jiných vlastností písma v konzoli pomocí ANSI escapové sekvence
public class font {
    //Modifikace
    public static String bold() {
        return "\033[1m";
    }

    public static String italic() {
        return "\033[3m";
    }

    public static String underline() {
        return "\033[4m";
    }

    public static String strikethrough() {
        return "\033[9m";
    }


    //Barvy
    public static String red() {
        return "\033[31m";
    }

    public static String green() {
        return "\033[32m";
    }

    public static String yellow() {
        return "\033[33m";
    }

    public static String blue() {
        return "\033[34m";
    }

    public static String magenta() {
        return "\033[35m";
    }

    public static String cyan() {
        return "\033[36m";
    }



    //Vlastní barvy
    public static String rgb(int r, int g, int b) {
        return ("\033[38;2;" + r + ";" + g + ";" + b + "m");
    }


    public static String orange() {
        return rgb(250, 140, 25);
    }

    public static String pink() {
        return rgb(250, 80, 225);
    }

    public static String lightBlue() {
        return rgb(90, 160, 235);
    }

    //Reset
    public static String reset() {
        return "\033[0m";
    }
}