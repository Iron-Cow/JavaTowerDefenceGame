package scenes;

import main.Constants;
import main.Game;
import main.GameStates;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static main.Constants.GAME_SIZE_PX;

public class Menu extends GameScene implements SceneMethods {

    private Random random = new Random();
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private int tileSize = Constants.TILE_SIZE;
    private MyButton bPlaying, bEdit, bSettings, bQuit;

    public Menu(Game game) {

        super(game);
        importImg();
        loadSprites();
        initButtons();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/img.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSprites(){
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x*tileSize, y*tileSize, tileSize, tileSize));
            }

        }
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = GAME_SIZE_PX / 2 - w / 2;
        int y = 150;
        int yOffset = 100;
        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Editor", x, y + yOffset, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset * 2, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 3, w, h);
    }


    @Override
    public void render(Graphics g) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                g.setColor(getRandomColor());
                g.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
                g.drawImage(sprites.get(random.nextInt(30)), x*tileSize, y*tileSize, null);
            }
        }

        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.PLAYING);
        }
        else if (bEdit.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.EDITING);
        }
        else if (bSettings.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.SETTINGS);
        }
        else if (bQuit.getBounds().contains(x, y)){
            System.exit(0);
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
        if (bPlaying.getBounds().contains(x, y)){
            bPlaying.setMouseOver(true);
        }
        else if (bEdit.getBounds().contains(x, y)){
            bEdit.setMouseOver(true);
        }
        else if (bSettings.getBounds().contains(x, y)){
            bSettings.setMouseOver(true);
        }
        else if (bQuit.getBounds().contains(x, y)){
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)){
            bPlaying.setMousePressed(true);
        }
        else if (bEdit.getBounds().contains(x, y)){
            bEdit.setMousePressed(true);
        }
        else if (bSettings.getBounds().contains(x, y)){
            bSettings.setMousePressed(true);
        }
        else if (bQuit.getBounds().contains(x, y)){
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    private Color getRandomColor(){
        return new Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }
}

