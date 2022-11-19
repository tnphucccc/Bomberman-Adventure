package Objects;

import Variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Constant.tileSize, Constant.tileSize, null);
    }
}
