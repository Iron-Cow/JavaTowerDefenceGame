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

public class Menu extends GameScene implements SceneMethods {

    private Random random = new Random();
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private BufferedImage img;
    private int tileSize = Constants.TILE_SIZE;
    private MyButton bPlaying, bSettings, bQuit;

    public Menu(Game game) {

        super(game);
        importImg();
        loadSprites();
        initButtons();
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
        bSettings.resetBooleans();
        bQuit.resetBooleans();
        if (bPlaying.getBounds().contains(x, y)){
            bPlaying.setMouseOver(true);
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
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;
        bPlaying = new MyButton("Play", x, y, w, h);
        bSettings = new MyButton("Settings", x, y + yOffset, w, h);
        bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
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

    private void loadSprites(){
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x*tileSize, y*tileSize, tileSize, tileSize));
            }

        }
    }



    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/img.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}