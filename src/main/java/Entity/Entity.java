package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x, y, speed, state;
    public BufferedImage up1, up2, up3, up4,
            down1, down2, down3, down4,
            left1, left2, left3, left4,
            right1, right2, right3, right4,
            die1, die2, die3, die4, die5, die6;
    public String direction;
    public int spriteCounter = 0, spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public abstract void setDefault();
    public abstract void update();
    public abstract void draw(Graphics2D g2);
}
