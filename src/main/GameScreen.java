package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.JPanel;
import java.awt.*;

import static main.Constants.BOTTOM_BAR_SIZE_PX;
import static main.Constants.GAME_SIZE_PX;

public class GameScreen extends JPanel {


    private Dimension size;
    private Game game;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game){
        this.game = game;

        setPanelSize();
    }

    private void setPanelSize() {
        size = new Dimension(GAME_SIZE_PX, GAME_SIZE_PX+BOTTOM_BAR_SIZE_PX);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void initInputs(){
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);
    }



}
