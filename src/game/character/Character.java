package game.character;


import java.util.ArrayList;

public class Character {
    private String name;

    private String location;

    private ArrayList<String> monologue;

    public Character() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMonologue(ArrayList<String> monologue) {
        this.monologue = monologue;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getMonologue() {
        return monologue;
    }
}
