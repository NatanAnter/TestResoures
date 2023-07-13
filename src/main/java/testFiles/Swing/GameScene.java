package testFiles.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {
    private Player player;
    private boolean[] pressedKeys;
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int GAME_SPEED_FAST = 3;
    public static final int GAME_SPEED_NORMAL = 10;
    public static final int GAME_SPEED_SLOW = 15;
    private Obstcale[] obstcales;
    public static final int WAITE_TIME = 3000;
    private int deathTimes;

    public GameScene(){
        this.setBackground(Color.BLUE);
        this.player = new Player(200,200);
        this.pressedKeys = new boolean[4];
        this.obstcales = new Obstcale[5];

        for (int i = 0; i < obstcales.length; i++) {
            Obstcale obstcale = new Obstcale(this.player.getX()+(i+1)*150, Window.WINDOW_HEIGHT/2);
            obstcales[i] = obstcale;
            obstcale.start();

        }
        this.mainGameLoop();
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.player.paint(graphics);
        for (int i = 0; i < this.obstcales.length; i++) {
            this.obstcales[i].paint(graphics);
        }
    }
    public void mainGameLoop(){
        new Thread(() ->{
            int waitBeforeRevive = 0;
            while (true){

                if(!this.player.isAlive()){
                    waitBeforeRevive++;
                    if(waitBeforeRevive>300) {
                        this.player.revive();
                        waitBeforeRevive =0;
                    }
                }

                int dx = 0;
                int dy =0;
                if(this.pressedKeys[DOWN])
                    dy++;
                if(this.pressedKeys[UP])
                    dy--;
                if(this.pressedKeys[LEFT])
                    dx--;
                if(this.pressedKeys[RIGHT])
                    dx++;
                this.player.move(dx,dy);
                Rectangle playerRect = this.player.cacluclateRectangle();
                for (int i = 0; i < this.obstcales.length; i++) {
                    Rectangle obstractRect = this.obstcales[i].cacluclateRectangle();
                    if(Utils.checkCollision(playerRect, obstractRect))
                        player.kill();
                }
                Utils.sleep(GAME_SPEED_NORMAL);
                repaint();
            }
        }).start();
    }

    public void keyTyped(KeyEvent e) {
//        System.out.println("pressed " + e.getKeyCode());

    }

    public void keyPressed(KeyEvent e) {
        Integer toPress = null;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            toPress = RIGHT;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            toPress = LEFT;
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            toPress = UP;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            toPress = DOWN;
        if (toPress != null) {
            this.pressedKeys[toPress] = true;

        }
    }

    public void keyReleased(KeyEvent e) {
        Integer toRelease = null;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            toRelease = RIGHT;
        else if(e.getKeyCode()==KeyEvent.VK_LEFT)
            toRelease = LEFT;
        else if(e.getKeyCode()==KeyEvent.VK_UP)
            toRelease = UP;
        else if(e.getKeyCode()==KeyEvent.VK_DOWN)
            toRelease = DOWN;
        if(toRelease!=null)
            this.pressedKeys[toRelease]=false;

    }
}
