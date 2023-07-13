package testFiles.Swing;

import javax.swing.*;

public class Window extends JFrame {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;

    public Window() {
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        GameScene gameScene = new GameScene();
        this.add(gameScene);
    }

    public void showWindow() {
        this.setVisible(true);
    }
}
