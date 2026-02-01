package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.character.Character;


public class Mluv extends Command {
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

    @Override
    public boolean exit() {
        return false;
    }
}
