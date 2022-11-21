package GUI;

import Controls.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pause {
    KeyHandler keyH;
    BufferedImage pause;

    boolean isPaused;
    public int status = 0; //0 = not paused, 1 = paused

    Pause(boolean isPaused, KeyHandler keyH){
        this.isPaused = isPaused;
        this.keyH = keyH;

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/main/resources/Menu/Pause.png"));
            pause = spriteSheet.getSubimage(0, 0, 308, 57);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseGame(){
        if (status == 0){
            if(keyH.enterPressed){
                this.isPaused = true;
                status++;

            } else this.isPaused = false;

        } else if (status == 1){
            if(keyH.enterPressed){
                this.isPaused = false;
                status--;

            } else this.isPaused = true;
        }
    }
}
