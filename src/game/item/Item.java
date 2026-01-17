package game.item;

public class Item {
    private String name;
    private boolean isMovable;
    private boolean isInteractable; //Is usable


    public Item(String name) {
        this.name = name;
        this.isMovable = false;
        this.isInteractable = false;
    }

    public Item(String name, boolean isMovable, boolean isInteractable) {
        this.name = name;
        this.isMovable = isMovable;
        this.isInteractable = isInteractable;
    }
}
