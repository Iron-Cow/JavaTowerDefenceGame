package objects;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;
    private int id;
    private String name;
    public Tile(BufferedImage sprite, int id, String name){
        this.sprite = sprite;
        this.id = id;
        this.name = name;

    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
