package ui;

import main.GameStates;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Constants.GAME_SIZE_PX;

public class ActionBar extends Bar {
    private Playing playing;
    private MyButton bMenu;


    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        initButtons();
    }

    private void initButtons() {

        bMenu = new MyButton("Menu", 2, GAME_SIZE_PX + 2, 100, 30);


    }

    public void draw(Graphics g) {

        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);

    }

    private BufferedImage getButtImg(int id) {
        return playing.getGame().getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)) {
            GameStates.setGameState(GameStates.MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.resetBooleans();
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }

}
