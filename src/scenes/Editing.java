package scenes;

import helpers.LoadSave;
import main.Constants;
import main.Game;
import objects.Tile;
import ui.ToolBar;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Constants.*;

public class Editing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private boolean drawSelectedTile;

    private int lastTileX;
    private int lastTileY;
    private int lastTileId;

    private ToolBar toolBar;
    private Game game;

    public Editing(Game game) {
        super(game);
        this.game = game;
        this.toolBar = new ToolBar(0, GAME_SIZE_PX, GAME_SIZE_PX, BOTTOM_BAR_SIZE_PX, this);
        loadDefaultLevel();

    }

    private void loadDefaultLevel() {

        lvl = LoadSave.GetLevelData("new_level");
    }

    @Override
    public void render(Graphics g) {

        drawLevel(g);
        drawSelectedTile(g);
        toolBar.draw(g);

    }

    private void drawLevel(Graphics g){

        // lvl
        // tile manager

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x* Constants.TILE_SIZE, y*Constants.TILE_SIZE, null);
            }
        }
    }

    private BufferedImage getSprite(int spriteId){
        return game.getTileManager().getSprite(spriteId);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y >= GAME_SIZE_PX){
            toolBar.mouseClicked(x, y);
        }else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y >= GAME_SIZE_PX){
            toolBar.mouseMoved(x, y);
        }else{
            drawSelectedTile = true;
            mouseX = x;
            mouseY = y;
        }

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y){
        changeTile(x, y);
    }

    public void saveLevel() {
        LoadSave.SaveLevel("new_level", lvl);
        game.getPlaying().setLevel(lvl);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelectedTile){
            mouseX -= mouseX % TILE_SIZE;
            mouseY -= mouseY % TILE_SIZE;
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, null);
        }
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            // tiles indexes
            int tileX = (int) x / TILE_SIZE;
            int tileY = (int) y / TILE_SIZE;
            if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
                return;
            }
            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();
            lvl[tileY][tileX] = selectedTile.getId();
        }
    }

}


