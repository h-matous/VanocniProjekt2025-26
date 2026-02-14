package game.ui;

import game.GameData;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Třída Loader má na starost načíst Herní svět z JSON souboru
 */
public class Loader {
    /**
     * Načte herní data z JSON souboru
     * @param resourcePath cesta k JSON resource souboru
     * @return vrací instanci objektu s načtenými daty
     * @throws Exception vyhazuje vyjímku např. pokud soubor neexistuje
     */
    public static GameData loadGameData(String resourcePath) throws Exception {
        ObjectMapper parser = new ObjectMapper();

        try {
            InputStream input = new FileInputStream(resourcePath);

            return parser.readValue(input, GameData.class);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor k načtení světa \"resource/gamedata.json\" nebyl nalezen! Nelze spustit hru!" + "\n" + e.getMessage());
        }
        catch (StreamReadException e) {
            throw new RuntimeException("Vyskytl se problém se zpracováním streamu k načtení herního světa! Nelze spustit hru!" + "\n" + e.getMessage());
        }
        catch (DatabindException e) {
            throw new RuntimeException("Nepovedlo se převést JSON na objekt herního světa! Nelze spustit hru!" + "\n" + e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException("Nepovedlo se zahájit komunikaci s JSON souborem k načtení herního světa! Nelze spustit hru!" + "\n" + e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("Nelze načíst herní svět!" + "\n" + e.getMessage());

        }
    }
}
