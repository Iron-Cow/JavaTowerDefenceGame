package ui;

import main.GameStates;
import objects.Tile;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.Constants.GAME_SIZE_PX;

public class ToolBar extends Bar{

    private MyButton bMenu, bSave;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    private Tile selectedTile;
    Editing editing;

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2, GAME_SIZE_PX + 2, 100, 30);
        bSave = new MyButton("Save", 2, GAME_SIZE_PX + 2 + bMenu.height + 10, 100, 30);


        // Tile types section
        int w = 50;
        int h = w;
        int x = 110;
        int y = GAME_SIZE_PX + 10;
        int xOffset = (int) (w * 1.1f);
        int i = 0;
        for (Tile tile: editing.getGame().getTileManager().tiles){
            tileButtons.add(new MyButton(tile.getName(), x + xOffset * i, y, w, h, i));
            i++;
        }
    }

    public void draw(Graphics g){

        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);
        drawTileButtons(g);
        drawSelectedTile(g);

    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(), GAME_SIZE_PX - 100, GAME_SIZE_PX + 10, 50, 50, null);
            g.setColor(Color.BLACK);
            g.drawRect(GAME_SIZE_PX - 100, GAME_SIZE_PX + 10, 50, 50);
        }
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton b: tileButtons) {
            g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

            // Mouseover
            if (b.isMouseOver()){
                g.setColor(Color.white);
            }else{
                g.setColor(Color.BLACK);
            }

            // Border
            g.drawRect(b.x, b.y, b.width, b.height);

            // MousePressed
            if (b.isMousePressed()) {
                g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
                g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
            }
        }
    }

    private BufferedImage getButtImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.MENU);
        }else if(bSave.getBounds().contains(x, y)){
            saveLevel();
        }else{
            for (MyButton b: tileButtons) {
                if(b.getBounds().contains(x, y)){
                    selectedTile = editing.getGame().getTileManager().getTile(b.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }

            }
        }
    }

    private void saveLevel() {
        editing.saveLevel();
    }

    public void mouseMoved(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        for (MyButton b: tileButtons){
            b.setMouseOver(false);
        }
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        } else if (bSave.getBounds().contains(x, y)){
            bSave.setMouseOver(true);
        }else {
            for (MyButton b: tileButtons){
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }else if (bSave.getBounds().contains(x, y)){
            bSave.setMousePressed(true);
        }else {
            for (MyButton b: tileButtons){
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        for (MyButton b: tileButtons){
            b.resetBooleans();
        }
    }

}
