package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.character.Character;


/**
 * Třída Mluv reprezentuje Command, který umožňuje Hráči mluvit s Postavami různě po mapě
 */
public class Mluv extends Command {
    /**
     * Metoda execute u příkazu Mluv vypíše Hráči to co mu řekne daná Postava
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        String characterName = param.split(" ")[0].trim();

        Character characterToTalkTo;

        try {
            characterToTalkTo = world.findCharacter(characterName);
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }


        //Jestli je hráč ve stejné místnosti jako postava se kterou chce mluvit
        if (world.playerInTheRoomAsCharacter(characterToTalkTo)) {
            return characterToTalkTo.getName() + ": " + characterToTalkTo.getProgressingMonologue();
        }

        return "Tato postava je v jiné místnosti než hráč!";
    }

    /**
     * Metoda exit() zjišťuje, jestli po zavolání tohoto příkazu má hra zrovna skončit, v tomto případě nemá
     * @return boolean jestli má hra po tomto příkazu skončit (false)
     */
    @Override
    public boolean exit() {
        return false;
    }
}
