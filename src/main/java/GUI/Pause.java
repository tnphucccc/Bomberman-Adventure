package GUI;

import Controls.KeyHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pause {
    public int status = 0; //0 = not paused, 1 = paused
    KeyHandler keyH = Window.getKeyH();
    BufferedImage pause;
    boolean isPaused, flag;

    Pause(boolean isPaused) {
        this.isPaused = isPaused;

        try {
            pause = ImageIO.read(new File("src/main/resources/Menu/Pause.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void pauseGame() {
        if (keyH.pausePressed) {
            flag = true;

        }
        if (!keyH.pausePressed && flag) {
            status = (status + 1) % 2; //pause game
            this.isPaused = !this.isPaused; //toggle
            flag = false;
        }
    }

    public void draw(Graphics g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
        g2.drawImage(pause, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
