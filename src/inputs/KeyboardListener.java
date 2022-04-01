package inputs;

import main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("A is Pressed!!!");
            GameStates.gameState = MENU;
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("S is Pressed!!!");
            GameStates.gameState = PLAYING;
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("D is Pressed!!!");
            GameStates.gameState = SETTINGS;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
