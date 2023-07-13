package testFiles.Swing;

import java.awt.*;

public class Utils {
    public static void sleep(int milliSecond){
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static Boolean checkCollision(Rectangle rect1,Rectangle rect2){
        Boolean collision  = false;
        if(rect1.intersects(rect2)){
            collision = true;
        }
        return collision;
    }
}
