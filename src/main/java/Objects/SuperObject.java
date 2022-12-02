package Objects;

import GUI.GameScene;
import Variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, Constant.TILE_SIZE, Constant.TILE_SIZE);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public void draw(Graphics2D g2) {

        int screenX = x - GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X;
        int screenY = y - GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y;

        if (x + Constant.TILE_SIZE > GameScene.getPlayer().x - Constant.PLAYER_SCREEN_X &&
                x - Constant.TILE_SIZE < GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X &&
                y + Constant.TILE_SIZE > GameScene.getPlayer().y - Constant.PLAYER_SCREEN_Y &&
                y - Constant.TILE_SIZE < GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y)
        {
            g2.drawImage(image, screenX, screenY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
        }
    }
}
