package game;

import game.command.CommandHandler;
import game.ui.ConsoleUI;
import game.ui.UI;

import game.ui.Loader;
import game.ui.font;

import game.command.finalGuessingMinigame;


/**
 * Třída Game slouží k zahájení hry a inicializaci prostředí ve kterém se bude odehrávat
 */
public class Game {
    private UI ui;

    private CommandHandler cmdHandler;

    private GameData world;

    private finalGuessingMinigame finalMinigame;


    private final String gameDataResourcePath;


    /**
     * Konstruktor načte veškeré herní data, inicializuje Hráče a uživatelské rozhraní
     */
    public Game() {
        this.ui = new ConsoleUI();

        this.gameDataResourcePath = "gamedata.json"; //původně resource/gamedata.json, když se načítalo ze souboru

        try {
            this.world = Loader.loadGameData(gameDataResourcePath);
        }
        catch (Exception e) {
            ui.println(e.getMessage());
        }


        this.finalMinigame = new finalGuessingMinigame(world, ui);
        this.cmdHandler = new CommandHandler(finalMinigame);

    }


    /**
     * Metoda play() slouží k zahájení celé hry
     */
    public void play() {
        boolean isNewGame;

        try {
            //Zobrazení menu
            isNewGame = menu();

            //Jestli je zahájena nová hra, vypíšeme úvod
            if (isNewGame) {
                uvod();
            }

            //Zavolání např. nějaké fileWrite metody
            while (!cmdHandler.isAboutToExit()) {
                ui.println("Nacházíte se v místnosti: " + font.lightBlue() + world.getPlayerRoom().getName() + font.reset());
                ui.println("Můžete se posunout do místností: " + world.getPlayerRoom().availableRoomNamesText());

                if (world.getPlayer().getInventory() != null) {
                    ui.println("V inventáři máte item: " + font.yellow() + world.getPlayer().getInventory().getName() + font.reset());
                }


                ui.print("\nZadejte příkaz >>");
                String userCommand = ui.scanNextLine();

                ui.println(">> " + cmdHandler.fetchDecodeExecuteCommand(userCommand, world, world.getPlayer()));
                ui.print("\n");
            }
        }
        catch (Exception e) {
            ui.println(e.getMessage());
        }
    }


    /**
     * Slouží k zobrazení názvu hry a případně zeptání se, zdali chce hrát Hráč hru novou nebo uložený save
     * @return vrací boolean, jestli je zahájena nová hra
     */
    private boolean menu() {
        ui.println(font.bold() + world.getGameName() + font.reset());
        ui.print("\n");

        GameData worldFromSave;

        //Zkouška načíst save souboru, pokud se nepovede (např. soubor neexistuje), tak je jasné, že Hráč bude začínat hru novou
        try {
            worldFromSave = Loader.loadFromSave(world.getGameSavePath());
        }
        catch (Exception e) {
            return true; //Vyhodila se vyjímka, když se nepovedl načíst save.dat, takže neexistuje předchozí save, takže se bude začínat nová hra
        }

        //Jinak se zeptáme uživatele jestli chce pokračovat nebo začít hru novou
        int scannedInt = -1;

        while (scannedInt != 0 && scannedInt != 1) {
            ui.println(font.bold() + "Byla nalezena rozehraná hra... Zvolte možnost, kterou chcete provést:" + font.reset());
            ui.println(font.bold() + "[0]" + font.reset() + " Začít novou hru");
            ui.println(font.bold() + "[1]" + font.reset() + " Pokračovat ve hře");
            ui.print("\n>>");
            ui.scanNextLine();
            ui.print("\n");

            try {
                scannedInt = Integer.parseInt(ui.getLastString());
            }
            catch (NumberFormatException e) {
                ui.println(font.red() + "Nezadali jste platné číslo..." + font.reset());
            }

            //Check podmínek
            if (scannedInt == 0) {
                return true;
            }

            if (scannedInt == 1) {
                world = worldFromSave;
                return false;
            }

            ui.println(font.red() + "Zadejte jedno z uvedených čísel..." + font.reset());
            ui.println("\n");
        }

        return true;
    }

    /**
     * Slouží k seznámení Hráče s příběhem, spustí se jen na začátku nové hry
     */
    private void uvod() {
        if (!world.getCharacters().isEmpty()) {
            //Postava na první pozici v ArrayListu (podle JSON struktury) se úvodně "seznámí" s hráčem jako první a použije se jeden z jejich monologů jako právě začáteční k uvedení hráče do děje a tím se hned i použije a progresuje se na další, použije se jen pokud jsou alespoň 2 monology u této postavy, protože pak by s touto postavou hráč nemohl mluvit znovu.
            if (world.getCharacters().getFirst().getMonologue().size() > 1) {
                ui.println(cmdHandler.fetchDecodeExecuteCommand("Mluv " + world.getCharacters().getFirst().getName(), world, world.getPlayer()));
                world.getCharacters().getFirst().progressMonologue();
                ui.println("\n");
            }
        }
    }
}