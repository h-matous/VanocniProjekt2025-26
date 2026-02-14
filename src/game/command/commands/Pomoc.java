package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;


public class Pomoc extends Command {

    @Override
    public String execute(String param, GameData world, Player player) {
        return "K dispozici jsou příkazy:\n" +
                "jdi <místnosti>            : pohyb mezi propojenými místnostmi\n" +
                "konec                      : ukončení hry\n" +
                "pomoc                      : zobrazení dostupných příkazů\n" +
                "napoveda                   : nápověda pro postup ve hře\n" +
                "vezmi <předmět>            : vzít konkrétní předmět z místnosti\n" +
                "poloz                      : položit předmět v inventáři\n" +
                "pouzij <předmět>           : použít předmět v místnosti\n" +
                "mluv <postava>             : mluvit s konkrétní postavou\n" +
                "prozkoumat                 : zjištění informací o místnosti\n" +
                "zkombinovat                : zkombinování předmětů do jiného předmětu\n";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
