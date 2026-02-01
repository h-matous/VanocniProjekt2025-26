package game.command.commands;

import game.command.Command;

import game.GameData;

public class Jdi extends Command {
    @Override
    public String execute(String param, GameData world) {
        String locationName = param.split(" ")[0];

        if (world.playerNextToRoom(locationName)) {
            world.setPlayerRoomName(world.findRoom(locationName).getName());

            return "Přicházíte do místnosti: " + world.getPlayerRoomName();
        }

        return "Nelze se posunout!";
    }
    //TODO: napsat třeba, že lokace neexistuje místo toho, že se vypisuje "Nelze se posunout!" nebo že se k daný lokaci nemuze přímo dostat.

    @Override
    public boolean exit() {
        return false;
    }
}
