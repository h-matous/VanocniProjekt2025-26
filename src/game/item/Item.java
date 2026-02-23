package game.item;

/**
 * Třída Item reprezentuje Item/Předmět s názvem, který se nachází v nějaké Místnosti, může být movitý, Použitelný nebo Kombinovatelný
 */
public class Item {
    private String name;

    private String location;

    private boolean movable; //allows to be moved
    private boolean interactable; //is usable

    private boolean combinable;

    /**
     * Prázdný konstruktor
     */
    public Item() {}

    /**
     * Plně parametrický konstruktor
     * @param name název Itemu/Předmětu
     * @param location název Místnosti, kde se nachází
     * @param movable boolean, který udává, zdali je movitý
     * @param interactable boolean, který udává, zdali je použitelný
     * @param combinable boolean, který udává, zdali je kombinovatelný
     */
    public Item(String name, String location, boolean movable, boolean interactable, boolean combinable) {
        this.name = name;
        this.location = location;
        this.movable = movable;
        this.interactable = interactable;
        this.combinable = combinable;
    }

    /**
     * Slouží k zjištění názvu Itemu/Předmětu
     * @return vrací String s názvem
     */
    public String getName() {
        return name;
    }

    /**
     * Slouží k zjištění názvu Místnosti kde se Item/Předmět nachází
     * @return vrací String jako název Místnosti, kde se právě nachází
     */
    public String getLocation() {
        return location;
    }

    /**
     * Slouží k nastavení názvu Itemu/Předmětu
     * @param name název jako String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Slouží k zjištění, zdali je Item/Předmět movitý
     * @return vrací boolean, jestli je movitý
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Slouží k nastavení movitosti Itemu/Předmětu
     * @param movable boolean reprezentující movitost
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    /**
     * Slouží k zjištění Použitelnosti Itemu/Předmětu
     * @return vrací boolean, jestli je Použitelný
     */
    public boolean isInteractable() {
        return interactable;
    }

    /**
     * Slouží k nastavení Použitelnosti Itemu/Předmětu
     * @param interactable boolean reprezentující použitelnost
     */
    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    /**
     * Slouží k nastavení názvu Místnosti ve které se má nacházet
     * @param location String reprezentující název Místnosti ve které se bude nacházet
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Slouží k zjištění, zdali je Item/Předmět kombinovatelný
     * @return vrací boolean jestli je kombinovatelný
     */
    public boolean isCombinable() {
        return combinable;
    }

    /**
     * Slouží k nastavení, zdali bude Item/Předmět kombinovatelný
     * @param combinable boolean reprezentující kombinovatelnost
     */
    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }
}
