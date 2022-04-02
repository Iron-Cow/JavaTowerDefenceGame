package scenes;

import helpers.LevelBuilder;
import helpers.LoadSave;
import main.Constants;
import main.Game;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Constants.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;

    private int mouseX, mouseY;
    private ActionBar bottomBar;
    Game game;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        this.game = game;

        bottomBar = new ActionBar(0, GAME_SIZE_PX, GAME_SIZE_PX, BOTTOM_BAR_SIZE_PX, this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
    }


    @Override
    public void render(Graphics g) {
        drawLevel(g);
        bottomBar.draw(g);

    }

    private void drawLevel(Graphics g){
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
            bottomBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y >= GAME_SIZE_PX){
            bottomBar.mouseMoved(x, y);
        }else{
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

    @Override
    public void mouseDragged(int x, int y){
        if(y >= GAME_SIZE_PX){
//            bottomBar.mouseClicked(x, y);
        }
    }


    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }
}