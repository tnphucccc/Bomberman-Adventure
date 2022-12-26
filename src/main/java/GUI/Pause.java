package GUI;

import Controls.KeyHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pause {
    KeyHandler keyH = Window.getKeyH();
    BufferedImage pause;
    GameScene gameScene;
    boolean flag;

    public static Pause instance = null;
    public static Pause getInstance(GameScene gameScene){
        if(Pause.instance == null){
            Pause.instance = new Pause(gameScene);
        }
        return Pause.instance;
    }

    Pause(GameScene gameScene) {
        this.gameScene = gameScene;
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
            gameScene.isPaused = !gameScene.isPaused; //toggle
            flag = false;
        }
    }

    public void draw(Graphics g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
        g2.drawImage(pause, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
