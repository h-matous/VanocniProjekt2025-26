package game.item;

public class Item {
    private String name;
    private boolean movable;
    private boolean interactable; //Is usable
    private String location;

    public Item() {}


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isInteractable() {
        return interactable;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
