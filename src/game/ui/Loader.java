package game.ui;

import game.GameData;

import java.io.InputStream;
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
     */
    public static GameData loadGameData(String resourcePath) {
        //Vytvoření objektu pro práci s JSON souborem
        ObjectMapper parser = new ObjectMapper();

        //Šlo by i třeba try (InputStream is = GameData.class.getResourceAsStream("/" + resourcePath))
        //Načtení souboru gamedata.json, musí být ve složce "resource", která je označená jako "Resources Root" složka projektu
        try (InputStream inputStream = Loader.class.getClassLoader().getResourceAsStream(resourcePath)) { //Automaticky zavře inputStream
            //původně InputStream input = new FileInputStream(resourcePath), když se ještě načítalo z externího souboru

            //Ověření, zdali soubor existuje
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource nebyl nalezen: " + resourcePath);
            }

            //Přečte celý JSON soubor a vytvoří instanci GameData.class, naplní všechny vlastnosti podle názvů klíčů v JSON souboru, vrátí se hotový objekt
            return parser.readValue(inputStream, GameData.class);
            //U GSONu by se použilo return gson.fromJson(new InputStreamReader(is, StandartCharsets.UTF_8));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor k načtení světa \"" + resourcePath + "\" nebyl nalezen! Nelze spustit hru!" + "\n" + e.getMessage());
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
