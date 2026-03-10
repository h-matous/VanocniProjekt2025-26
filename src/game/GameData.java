package game;

import game.item.Item;
import game.character.Character;
import game.room.Room;
import game.ui.UI;

import java.util.ArrayList;

import java.io.Serializable;


/**
 * Třída GameData obsahuje veškerá data o Herním světě včetně aktuální pozice Hráče
 * Reprezentuje herní data načtená z JSON souboru a uchovává veškerá statická data jako třeba Itemy, Postavya Místnosti
 */
public class GameData implements Serializable {
    private String gameName;

    //player
    private Player player;

    private String gameSavePath;

    //items/objects, characters, locations/rooms, quests?
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private ArrayList<Room> rooms;

    //Název místnosti ve které se právě Hráč nachází
    private String playerRoomName;

    //Zprávy požadavků progresových fází
    private ArrayList<String> endingPhasesRequirementMessages;

    //Zpráva k hádací minihře
    private String endingPhaseGuessingMinigameMessage;

    //Příběh k výhře
    private String endingStory;

    //Nápověda
    private String hint;

    //Jestli má spustit minihru
    private boolean playFinalGuessingMinigame;

    /**
     * Prázdný konstruktor pro načítání Herního světa GameData s jackson
     */
    public GameData() {}

    /**
     * Slouží k získání instance Hráče
     * @return vrací instanci Hráče
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Slouží k nastavení Hráče
     * @param player Hráč jako objekt
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Slouží k získání cesty k souboru na případné uložení hry
     * @return vrací cestu k souboru jako String
     */
    public String getGameSavePath() {
        return gameSavePath;
    }

    /**
     * Slouží k nastavení cesty, kde se bude nacházet uložení hry
     * @param gameSavePath cesta k nastení jako String
     */
    public void setGameSavePath(String gameSavePath) {
        this.gameSavePath = gameSavePath;
    }

    /**
     * Spouštění finální hádací minihry, kterou následuje konec a výhra hry
     */
    public void playFinalGuessingMinigame() {
        playFinalGuessingMinigame = true;
    }



    /**
     * Slouží k zjištění zdali hráč podle příběhu hry dokončil první úkol, díky kterému může postupovat ve hře a může vykonat další úkol
     * @return vrací boolean jestli tuto progresovou fázi Hráč dokončil
     */
    public boolean isFirstProgressingPhaseDone() {
        //Podle navržení JSONu, je vyžadováno, aby byl 1. Item (Pojistka) umíštěn v 2. Lokaci/Místnosti (Chodba).
        //Navrženo podle systému indexování v ArrayListu, aby bylo možné upravovat příběh a určité Itemy se musely umístit do určitých místností podle gameDesignu.

        if (items.isEmpty()) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Itemů. Chybný JSON!");
        if (rooms.size() < 2) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Místností. Chybný JSON!");

        Item firstItemFromJSON = items.getFirst();
        //V tomto typu hry tato podmínka nebude muset být použita, protože Pojistka už je na mapě od začátku a není nutno ji spawnovat později, protože není kombinovatelná
        if (firstItemFromJSON.getLocation().isEmpty()) return false;
        Room firstItemLocation = findRoom(firstItemFromJSON.getLocation());
        Room secondRoomFromJSON = rooms.get(1);

        //Pokud je ten Item v požadované Místnosti
        return firstItemLocation.equals(secondRoomFromJSON);
    }

    /**
     * Slouží k zjištění zdali hráč podle příběhu hry dokončil druhý úkol, díky kterému může poté navázat na poslední úkol
     * @return vrací boolean jestli tuto progresovou fázi Hráč dokončil
     */
    public boolean isSecondProgressingPhaseDone() {
        //Podle navržení JSONu, je vyžadováno, aby byl poslední Item (Baterie) umíštěn v 2. Lokaci/Místnosti (Strojovna).
        //Navrženo podle systému indexování v ArrayListu, aby bylo možné upravovat příběh a určité Itemy se musely umístit do určitých místností podle gameDesignu.

        if (items.isEmpty()) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Itemů. Chybný JSON!");
        if (rooms.size() < 2) throw new RuntimeException("Nelze progresovat ve hře, nedostatek Místností. Chybný JSON!");

        Item lastItemFromJSON = items.getLast();
        //Jestli je název lokace Baterie "", znamená, že ji ještě hráč nevyrobil v laboratoři, ""/isEmpty() znamená tedy, že ještě nebyla spawnutá do mapy, takže tato progresová fáze není hotova
        if (lastItemFromJSON.getLocation().isEmpty()) return false;
        Room lastItemLocation = findRoom(lastItemFromJSON.getLocation());
        Room lastRoomFromJSON = rooms.getLast();

        //Pokud je ten Item v požadované Místnosti a zároveň pokud byla dokončená první fáze (což jakože byla, ale pro jistotu ;))
        return lastItemLocation.equals(lastRoomFromJSON) && isFirstProgressingPhaseDone();
    }


    /**
     * Slouží k získání textového řetězce, který obsahuje výčet Itemů z ArrayListu
     * @param items ArrayList všech Itemů k výčtu
     * @return vrací String výčtu těchto Itemů
     */
    public String getStringOfItemsText(ArrayList<Item> items) {
        StringBuilder toReturn = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            toReturn.append(items.get(i).getName());

            if (i < items.size() - 1) {
                toReturn.append(", ");
            }
        }

        return toReturn.toString();
    }

    /**
     * Najde specifickou Místnost podle její jména
     * @param roomName název Místnosti k nalezení
     * @return shodující Místnost
     */
    public Room findRoom(String roomName) {
        for (Room room : rooms) {
            if (UI.toLowercaseAscii(room.getName()).equals(UI.toLowercaseAscii(roomName))) {
                return room;
            }
        }

        throw new IllegalArgumentException("Neexistuje lokace s názvem: \"" + roomName + "\"!");
    }


    /**
     * Získá Item z parametru příkazu, používáno u Commandů Vezmi a Pouzij
     * @param param parametr příkazu
     * @return shodující Item
     */
    public Item getItemFromParam(String param) {
        String itemName = param.split(" ")[0].trim();

        return findItem(itemName);
    }

    /**
     * Najde Místnost ve které se nachází určitý Item
     * @param item Item k získání Místnosti
     * @return Místnost ve které se nachází Item
     */
    public Room getItemLocationFromItem(Item item) {
        return findRoom(item.getLocation());
    }


    /**
     * Najde postavu podle jejího jména
     * @param characterName jméno postavy
     * @return Postavu Character se shodujícím jménem
     */
    public Character findCharacter(String characterName) {
        for (Character character : characters) {
            if (UI.toLowercaseAscii(character.getName()).equals(UI.toLowercaseAscii(characterName))) {
                return character;
            }
        }

        throw new IllegalArgumentException("Neexistuje postava s názvem: \"" + characterName + "\"");
    }


    /**
     * Najde specifický Item podle jeho názvu
     * @param itemName název hledaného Itemu
     * @return Item se shodujícím názvem
     */
    public Item findItem(String itemName) {
        for (Item item : items) {
            if (UI.toLowercaseAscii(item.getName()).equals(UI.toLowercaseAscii(itemName))) {
                return item;
            }
        }

        throw new IllegalArgumentException("Neexistuje item s názvem: \"" + itemName + "\"");
    }


    /**
     * Nastaví hráčovu Místnost, kde se právě nachází
     * @param playerRoom hráčova Místnost
     */
    public void setPlayerRoom(Room playerRoom) {
        this.playerRoomName = playerRoom.getName();
    }


    /**
     * Zjistí jestli se nachází Hráč vedle určité Místnosti
     * @param room Místnost k vedle které se potencionálně nachází Hráč
     * @return vrací boolean jestli se vedle Místnosti nachází
     */
    public boolean playerNextToRoom(Room room) {
        return getPlayerRoom().isNextToRoom(room);
    }

    /**
     * Zjistí jestli se nachází Hráč ve stejné Místnosti jako postava Character
     * @param character postava ke které chce hráč mluvit
     * @return vrací boolean jestli je Hráč ve stané místnosti jako postava
     */
    public boolean playerInTheRoomAsCharacter(Character character) {
        return getPlayerRoom().equals(findRoom(character.getLocation()));
    }

    /**
     * Slouží k získání Hráčovy Místnosti, kde se nachází
     * @return vrací Místnost ve které se Hráč nachází
     */
    public Room getPlayerRoom() {
        try {
            return findRoom(getPlayerRoomName());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Nelze načíst lokaci hráče! Špatný formát JSON souboru!");
        }
    }


    /**
     * Slouží k získání všech Itemů v Herním světě
     * @return vrací ArrayList všech Itemů
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Nastaví Hráčovu Místnost, kde se právě nachází
     * @param playerRoomName název konkrétní Místnosti
     */
    public void setPlayerRoomName(String playerRoomName) {
        this.playerRoomName = playerRoomName;
    }

    /**
     * Získá název Místnosti ve které se Hráč zrovna nachází
     * @return vrací název konkrétní místnosti
     */
    public String getPlayerRoomName() {
        return playerRoomName;
    }


    /**
     * Slouží k nastavení Itemů v Herním světě na konkrétní ArrayList všech Itemů
     * @param items ArrayList Itemů, které se nastaví
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Slouží k získání všech postav v Herním světě
     * @return vrací ArrayList všech těchto postav
     */
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * Slouží k nastavení postav v Herním světě na konkrétní ArrayList všech postav
     * @param characters ArrayList postav, které se nastaví
     */
    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }


    /**
     * Slouží k získání všech Místností v Herním světě
     * @return vrací ArrayList Místností
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Slouží k nastavení Místností v Herním světě na konkrétní ArrayList všech Místností
     * @param rooms ArrayList Místností, které se nastaví
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Slouží k získání zpráv požadavků, které je nutné dokončit pro dokončení hry
     * @return vrací ArrayList textových řetěžců, který obsahuje tyto zprávy
     */
    public ArrayList<String> getEndingPhasesRequirementMessages() {
        return endingPhasesRequirementMessages;
    }

    /**
     * Slouží k nastavení zpráv požadavků, které je nutné dokončit pro dokončení hry
     * @param endingPhasesRequirementMessages ArrayList Stringů těchto zpráv požadavků
     */
    public void setEndingPhasesRequirementMessages(ArrayList<String> endingPhasesRequirementMessages) {
        this.endingPhasesRequirementMessages = endingPhasesRequirementMessages;
    }

    /**
     * Slouží k získání zprávy, která se ukáže při hádací minihře, kdy hráč hádá/hledá specifické číslo
     * @return vrací tuto zprávu jako String
     */
    public String getEndingPhaseGuessingMinigameMessage() {
        return endingPhaseGuessingMinigameMessage;
    }

    /**
     * Slouží k nastavení zprávy, která se ukáže při hádací minihře, kdy hráč hádá/hledá specifické číslo
     * @param endingPhaseGuessingMinigameMessage tato zpráva jako String
     */
    public void setEndingPhaseGuessingMinigameMessage(String endingPhaseGuessingMinigameMessage) {
        this.endingPhaseGuessingMinigameMessage = endingPhaseGuessingMinigameMessage;
    }

    /**
     * Slouží k získání příběhu po konci hry
     * @return vrací příběh jako String
     */
    public String getEndingStory() {
        return endingStory;
    }

    /**
     * Slouží k nastavení příběhu po konci hry
     * @param endingStory příběh jako String
     */
    public void setEndingStory(String endingStory) {
        this.endingStory = endingStory;
    }

    /**
     * Slouží k získání nápovědy, kterou si hráč vyžádá, když neví jak pokračovat
     * @return vrací nápovědu jako String
     */
    public String getHint() {
        return hint;
    }

    /**
     * Slouží k nastavení nápovědy
     * @param hint nápověda jako String
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * Slouží ke kontrole, jestli má hra spustit finální hádací minihru
     * @return vrací boolean, jestli se má minihra spustit
     */
    public boolean shouldPlayFinalGuessingMinigame() {
        return playFinalGuessingMinigame;
    }

    /**
     * Slouží k nastavení, jestli má hra spustit finální hádací minihru
     * @param playFinalGuessingMinigame boolean jestli se má minihra spustit
     */
    public void setPlayFinalGuessingMinigame(boolean playFinalGuessingMinigame) {
        this.playFinalGuessingMinigame = playFinalGuessingMinigame;
    }

    /**
     * Slouží k získání názvu hry
     * @return vrací název hry jako String
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Slouží k nastavení názvu hry
     * @param gameName název hry jako String, který bude nastaven
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}