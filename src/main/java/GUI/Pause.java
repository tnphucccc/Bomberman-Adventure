package GUI;

import Controls.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pause {
    public int status = 0; //0 = not paused, 1 = paused
    KeyHandler keyH;
    BufferedImage pause;
    boolean isPaused, flag;
    Rect pauseRect;

    Pause(boolean isPaused, KeyHandler keyH) {
        this.isPaused = isPaused;
        this.keyH = keyH;

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/main/resources/Menu/Pause.png"));
            pause = spriteSheet.getSubimage(0, 0, 308, 57);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Pause location
        pauseRect = new Rect(254, 235, 308, 57);
    }

    public void pauseGame() {
        if (keyH.pausePressed) {
            flag = true;
        }
        if (!keyH.pausePressed && flag) {
            status = (status + 1) % 2;
            this.isPaused = !this.isPaused; //toggle
            flag = false;
        }
    }

    public void draw(Graphics g2) {
        g2.drawImage(pause, (int) pauseRect.x, (int) pauseRect.y, (int) pauseRect.width, (int) pauseRect.height, null);
    }
}
