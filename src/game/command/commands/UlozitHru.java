package game.command.commands;

import game.GameData;
import game.command.Command;
import game.Player;

import game.ui.font;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Třída UlozitHru reprezentuje Command, který umožňuje Hráči uložit hru
 */
public class UlozitHru extends Command {
    /**
     * Metoda execute u příkazu UlozitHru uloží hru do souboru
     *
     * @param param  String parametr, který byl uživatelem specifikován po příkazu
     * @param world  instance Herního světa
     * @param player instance Hráče
     * @return vrací String, který se vypíše
     */
    @Override
    public String execute(String param, GameData world, Player player) {
        //Serializace
        try {
            FileOutputStream fos = new FileOutputStream(world.getGameSavePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(world);
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            return font.red() + "Nepovedlo se zapsat do souboru \"" + world.getGameSavePath() + "\": " + e.getMessage() + font.reset();
        }


        return font.bold() + font.magenta() + "Hra byla uložena, můžete hru ukončit." + font.reset();
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
