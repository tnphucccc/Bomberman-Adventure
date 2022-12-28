package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
    public int x, y, speed, state, hitPoint;
    public BufferedImage[] up = new BufferedImage[4], down = new BufferedImage[4],
            left = new BufferedImage[4], right = new BufferedImage[4],bomb=new BufferedImage[4],
            die = new BufferedImage[6];
    public String direction,name;
    public int spriteCounter = 0, spriteNum = 1;
    public Rectangle solidArea;

    public boolean collisionOn = false;
    public ArrayList<Integer> InteractionBox = new ArrayList<>();
    public int solidAreaDefaultX, solidAreaDefaultY;

    //public abstract void setDefault();

    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public void setEntityInteractionBox(Entity entity) {
        this.InteractionBox.add(0, entity.y + entity.solidArea.y);//TopY
        this.InteractionBox.add(1, entity.x + entity.solidArea.x + entity.solidArea.width);//RightX
        this.InteractionBox.add(2, entity.y + entity.solidArea.y + entity.solidArea.height);//BottomY
        this.InteractionBox.add(3, entity.x + entity.solidArea.x);//Left
    }
    //getter && setter for x y but in tile
    public int getX() {
        return ((x + 16) / 48) * 48;
    }
    public int getY(){
        return ((y + 16) / 48) * 48;
    }

    public BufferedImage getEntityImage() {
        BufferedImage img = null;
        switch (direction) {
            case "up" -> img = getBufferedImage(up[0], up[1], up[2], up[3]);
            case "down" -> img = getBufferedImage(down[0], down[1], down[2], down[3]);
            case "left" -> img = getBufferedImage(left[0], left[1], left[2], left[3]);
            case "right" -> img = getBufferedImage(right[0], right[1], right[2], right[3]);
            case "bomb" -> img = getBufferedImage(bomb[0],bomb[1],bomb[2],bomb[3]);
        }
        return img;
    }

    public BufferedImage getBufferedImage(BufferedImage img1, BufferedImage img2,
                                          BufferedImage img3, BufferedImage img4) {
        switch (spriteNum) {
            case 1 -> {
                return img1;
            }
            case 2 -> {
                return img2;
            }
            case 3 -> {
                return img3;
            }
            case 4 -> {
                return img4;
            }
        }
        return null;
    }

    public BufferedImage getBufferedImage(BufferedImage img1, BufferedImage img2, BufferedImage img3,
                                          BufferedImage img4, BufferedImage img5, BufferedImage img6) {
        switch (spriteNum) {
            case 1 -> {
                return img1;
            }
            case 2 -> {
                return img2;
            }
            case 3 -> {
                return img3;
            }
            case 4 -> {
                return img4;
            }
            case 5 -> {
                return img5;
            }
            case 6 -> {
                return img6;
            }
        }
        return null;
    }
    public BufferedImage getBufferedImage(BufferedImage img1, BufferedImage img2, BufferedImage img3,
                                          BufferedImage img4, BufferedImage img5, BufferedImage img6,
                                          BufferedImage img7, BufferedImage img8) {
        switch (spriteNum) {
            case 1 -> {
                return img1;
            }
            case 2 -> {
                return img2;
            }
            case 3 -> {
                return img3;
            }
            case 4 -> {
                return img4;
            }
            case 5 -> {
                return img5;
            }
            case 6 -> {
                return img6;
            }
            case 7 -> {
                return img7;
            }
            case 8 -> {
                return img8;
            }
        }
        return null;
    }
}