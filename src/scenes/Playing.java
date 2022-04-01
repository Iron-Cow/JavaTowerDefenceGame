package scenes;

import helpers.LevelBuilder;
import main.Constants;
import main.Game;
import main.GameStates;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;
import ui.MyButton;

import java.awt.*;

import static main.Constants.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private boolean drawSelectedTile;


    private BottomBar bottomBar;
    private int lastTileX;
    private int lastTileY;
    private int lastTileId;

    public Playing(Game game) {
        super(game);
        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, GAME_SIZE_PX, GAME_SIZE_PX, BOTTOM_BAR_SIZE_PX, this);
    }



    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, GAME_SIZE_PX, GAME_SIZE_PX);

        // lvl
        // tile manager

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*Constants.TILE_SIZE, y*Constants.TILE_SIZE, null);
            }
        }

        bottomBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelectedTile){
            mouseX -= mouseX % TILE_SIZE;
            mouseY -= mouseY % TILE_SIZE;
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, null);
        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if(y >= GAME_SIZE_PX){
            bottomBar.mouseClicked(x, y);
        }else {
            changeTile(mouseX, mouseY);
        }
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

    @Override
    public void mouseMoved(int x, int y) {
        drawSelectedTile = false;
        if(y >= GAME_SIZE_PX){
            bottomBar.mouseMoved(x, y);
        }else{
            drawSelectedTile = true;
            mouseX = x;
            mouseY = y;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y >= GAME_SIZE_PX){
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }


    public TileManager getTileManager() {
        return tileManager;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    @Override
    public void mouseDragged(int x, int y){
        if(y >= GAME_SIZE_PX){
//            bottomBar.mouseClicked(x, y);
        }else {
            changeTile(x, y);
        }
    }
}