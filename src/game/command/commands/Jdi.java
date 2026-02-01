package game.command.commands;

import game.command.Command;
import game.GameData;
import game.Player;

import game.room.Room;


public class Jdi extends Command {
    @Override
    public String execute(String param, GameData world, Player player) {
        String locationName = param.split(" ")[0].trim();

        Room roomToMoveTo;

        try {
            roomToMoveTo = world.findRoom(locationName);
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (world.playerNextToRoom(roomToMoveTo)) {
            world.setPlayerRoom(roomToMoveTo);

            return "Přicházíte do místnosti: " + world.getPlayerRoom().getName();
        }

        return "Nelze se odsud přímo posunout do této místnosti!";
    }
    //TODO: napsat třeba, že lokace neexistuje místo toho, že se vypisuje "Nelze se posunout!" nebo že se k daný lokaci nemuze přímo dostat.

    @Override
    public boolean exit() {
        return false;
    }
}
