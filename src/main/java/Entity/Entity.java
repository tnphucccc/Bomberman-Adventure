package Entity;

import Variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    public ArrayList<Integer> InteractionBox = new ArrayList<>();
    public int solidAreaDefaultX, solidAreaDefaultY;

    public abstract void setDefault();

    public abstract void update(double dt);


    public void setEntityInteractionBox(Entity entity) {
        this.InteractionBox.add(0, entity.y + entity.solidArea.y);//TopY
        this.InteractionBox.add(1, entity.x + entity.solidArea.x + entity.solidArea.width);//RightX
        this.InteractionBox.add(2, entity.y + entity.solidArea.y + entity.solidArea.height);//BottomY
        this.InteractionBox.add(3, entity.x + entity.solidArea.x);//Left
    }
    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        switch (direction) {
            case "up" -> img = getBufferedImage(up1, up2, up3, up4);
            case "down" -> img = getBufferedImage(down1, down2, down3, down4);
            case "left" -> img = getBufferedImage(left1, left2, left3, left4);
            case "right" -> img = getBufferedImage(right1, right2, right3, right4);
        }
        g2.drawImage(img, x, y, Constant.original_tile_size * Constant.scale,
                Constant.original_tile_size * Constant.scale, null);
    }

    private BufferedImage getBufferedImage(BufferedImage img1,
                                           BufferedImage img2,
                                           BufferedImage img3,
                                           BufferedImage img4) {
        switch (spriteNum) {
            case 1 -> {return img1;}
            case 2 -> {return img2;}
            case 3 -> {return img3;}
            case 4 -> {return img4;}
            default -> {return null;}
        }
    }
}
