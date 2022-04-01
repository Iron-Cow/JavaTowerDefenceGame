package managers;

import helpers.LoadSave;
import main.Constants;
import objects.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS, WATER, ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();
    private int tileSize = Constants.TILE_SIZE;

    public TileManager(){
        loadAtlas();
        createTiles();

    }

    private void createTiles() {

        tiles.add(GRASS = new Tile(getSprite(9, 0)));
        tiles.add(WATER = new Tile(getSprite(0, 0)));
        tiles.add(ROAD = new Tile(getSprite(8, 0)));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    private BufferedImage getSprite(int xCord, int yCord){
        return atlas.getSubimage(xCord*tileSize, yCord*tileSize, tileSize, tileSize);
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }
}
