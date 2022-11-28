package Objects;

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
        g2.drawImage(image, x, y, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
