package main;

import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.swing.JFrame;


public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;




    private Render render;
    private Thread gameThread;

    // Classes
    private Menu menu;
    private Playing playing;
    private Settings settings;


    public Game(){
        initClasses();


        add(gameScreen);
        pack();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);

    }





    private void updateGame() {
//        updates++;
//        lastUpdate = System.nanoTime();
//        System.out.println("main.Game Updated");
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();

    }

    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        double timePerFrame = 1_000_000_000.0 /Constants.FPS;
        double timePerUpdate = 1_000_000_000.0 /Constants.TIME_PER_UPDATE;


        int frames = 0;
        int updates = 0;
        long lastTimeCheck = System.currentTimeMillis();

        long now;

        while (true) {
            now = System.nanoTime();
            // render

            // control frame rate
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }

            // update
            if (now - lastUpdate >= timePerUpdate){
                updateGame();
                lastUpdate = now;
                updates++;

            }

            // check fps ups
            if (System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + " | UPS" + updates);
                updates = 0;
                frames = 0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }
    }

    // getters and setters
    public Render getRender(){
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }
}
