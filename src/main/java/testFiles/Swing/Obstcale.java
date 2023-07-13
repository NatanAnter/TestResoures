package testFiles.Swing;

import java.awt.*;
import java.util.Random;

public class Obstcale extends Thread {
    public static final int SIZE = 30;
    private int x;
    private int y;
    public Obstcale(int x,int y){
        this.x =x;
        this.y=y;
    }
    public void paint(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fillOval(x,y,SIZE,SIZE);
    }
    public void run(){
        Random random = new Random();
        Boolean goingUp = random.nextBoolean();
        int speed = random.nextInt(5,35);
        while (true){
            if(!goingUp)
                this.y++;
            else
                this.y--;
            Utils.sleep(speed);
            if(this.y> Window.WINDOW_HEIGHT-SIZE||this.y<0)
                goingUp = !goingUp;
        }
    }
    public Rectangle cacluclateRectangle(){
        return new Rectangle(this.x,this.y,SIZE,SIZE);
    }

}
