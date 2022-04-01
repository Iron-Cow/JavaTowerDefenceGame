package ui;

import java.awt.*;

public class MyButton {
    public int x, y, width, height;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver;
    private boolean mousePressed;
    private int id = -1;


    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    // alternative constructor for tile buttons
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }

    public int getId() {
        return id;
    }

    public void draw(Graphics g){

        // Body
        drawBody(g);


        // Border
        drawBorder(g);


        // Text
        drawText(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        if(mousePressed){
            g.drawRect(x+1, y+1, width-2, height-2);
            g.drawRect(x+2, y+2, width-4, height-4);

        }
    }

    private void drawBody(Graphics g) {
        if (mouseOver){
            g.setColor(Color.gray);
        }else{
            g.setColor(Color.WHITE);
        }
        g.fillRect(x, y, width, height);
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x+(width - w)/2, y+(height+h/2)/2);
    }

    private void initBounds(){
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public boolean isMousePressed(){
        return mousePressed;
    }

}
