package game.command.commands;

import game.command.Command;

import game.GameData;

public class Jdi extends Command {
    @Override
    public String execute(String param, GameData world) {
        String locationName = param.split(" ")[0];

        if (world.findRoom(world.getPlayerRoomName()).isNextToRoom(locationName)) {
            world.setPlayerRoomName(world.findRoom(locationName).getName());

            return "Nacházíte se v místnosti: \"" + world.getPlayerRoomName() + "\"." + "\nMůžete se posunout do místností: " + world.findRoom(world.getPlayerRoomName()).availableRoomNamesText();
        }

        return "Nelze se posunout!" + "\nMůžete se posunout do místností: " + world.findRoom(world.getPlayerRoomName()).availableRoomNamesText();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
