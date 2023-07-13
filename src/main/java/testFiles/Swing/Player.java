package testFiles.Swing;

import java.awt.*;

public class Player {
    private int x;
    private int y;
    public static final int SIZE = 50;
    private boolean alive;
    public void kill(){
        alive = false;
    }
    public void revive(){
        alive = true;
        this.y = (Window.WINDOW_HEIGHT-SIZE)/2;
        this.x = 50;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getX(){
        return x;
    }

    public Player (int x, int y){
        this.x =x;
        this.y=y;
        this.alive = true;
    }
    public void paint (Graphics graphics){
        if(alive) {
//            ImageIcon ico = new ImageIcon("C:\\Users\\Natan\\Downloads\\download-removebg-preview.png");
//            ico.
//        }
            graphics.setColor(Color.BLACK);
            graphics.fillOval(this.x, this.y, SIZE, SIZE);
            graphics.fillRect(this.x, this.y + SIZE, SIZE, SIZE);
        }
    }
    public void move(int dx,int dy) {
        this.x += dx;
        this.y += dy;
    }
    public Rectangle cacluclateRectangle(){
        return new Rectangle(this.x,this.y,SIZE,SIZE);
    }

}
