package game.character;

import java.util.ArrayList;

import java.io.Serializable;


/**
 * Třída Character reprezentuje Postavu v herním světě
 */
public class Character implements Serializable {
    private String name;

    private String location;

    private ArrayList<String> monologue;

    /**
     * Prázdný konstruktor
     */
    public Character() {}

    /**
     * Plně parametrický konstruktor
     * @param name název Postavy
     * @param location název Místnosti, kde se nachází
     * @param monologue ArrayList Stringů všech monologů
     */
    public Character(String name, String location, ArrayList<String> monologue) {
        this.name = name;
        this.location = location;
        this.monologue = monologue;
    }


    /**
     * Slouží k získání monologu Postavy
     * @return vrací String obsahující monolog
     */
    public String getProgressingMonologue() {
        if (!monologue.isEmpty()) {
            return monologue.get(0);
        }

        return "";
    }

    /**
     * Slouží k progresu monologu
     */
    public void progressMonologue() {
        if (!monologue.isEmpty()) {
            monologue.remove(0);
        }
    }

    /**
     * Slouží k nastavení názvu Postavy
     * @param name nový název Postavy jako String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Slouží k nastavení nové lokace v jaké Místnosti se Postava nachází
     * @param location název nové Místnosti
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Slouží k nastavení monologu Postavy
     * @param monologue monolog Postavy jako ArrayList Stringů
     */
    public void setMonologue(ArrayList<String> monologue) {
        this.monologue = monologue;
    }

    /**
     * Slouží k získání názvu Postavy
     * @return vrací String názvu Postavy
     */
    public String getName() {
        return name;
    }

    /**
     * Slouží k získání názvu Místnosti, kde se Postava nachází
     * @return vrací String názvu lokace/Místnosti, kde právě Postava je
     */
    public String getLocation() {
        return location;
    }

    /**
     * Slouží k získání všech monologů Postavy
     * @return vrací ArrayList Stringů s monology
     */
    public ArrayList<String> getMonologue() {
        return monologue;
    }
}
