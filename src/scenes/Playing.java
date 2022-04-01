package scenes;

import helpers.LevelBuilder;
import main.Constants;
import main.Game;
import main.GameStates;
import managers.TileManager;
import ui.MyButton;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private TileManager tileManager;
    private MyButton bMenu;

    public Playing(Game game) {
        super(game);
        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 2;
        int y = 2;
        bMenu = new MyButton("Menu", x, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 640, 640);

        // lvl
        // tile manager

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*Constants.TILE_SIZE, y*Constants.TILE_SIZE, null);
            }
        }
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.resetBooleans();
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }


}