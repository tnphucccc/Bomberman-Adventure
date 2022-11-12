package Entity;

import Controls.keyHandler;
import GUI.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb {
    private int x, y;
    private String key = "";
    gamePanel gamepanel;
    keyHandler keyH;
    public Bomb(gamePanel gp, keyHandler keyH){
        this.gamepanel = gp;
        this.keyH = keyH;
    }

    public void update(int x,int y){
        if(keyH.spacePressed) {
            key = "space";
            this.x = x;
            this.y = y;
        } else {
            key ="";
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage img = null;

        if(key.equals("space")){
            try {
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/bomb1.gif")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(img, x, y, gamepanel.original_tile_size * gamePanel.scale,
                    gamepanel.original_tile_size * gamePanel.scale, null);
        }
    }

}
