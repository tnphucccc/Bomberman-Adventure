package Entity;

import GUI.BombExplodeMap;
import GUI.Camera;
import GUI.GameScene;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Bomb extends Entity {
    private final int bombSize = GameScene.bombSize;

    private final ArrayList<Bomb> bombList = new ArrayList<>(bombSize);

    private long timeStart = 0L;
    private int x, y;
    private String key = "";

    private int bombCounter = 0;
    public static int bombRadius;


    public Bomb(int x,int y,int radius) {
        this.x = x;
        this.y = y;
        bombRadius = radius;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        getBombImage();
        setDefault();
        update(x,y);
    }
    public Bomb (){}
    public void update(int x, int y) {
        key = "space";
        timeStart = System.nanoTime();
        
        // round x and y so the bomb is placed in the middle of the tile
        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;
    }

    //draw bomb on the map with gif
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
            if (key.equals("space")) {
                if ((System.nanoTime() - timeStart)/Constant.Tera < 2) {//planting for 2s
                    update();
                    g2.drawImage(img, Camera.setXCord(x), Camera.setYCord(y), Constant.TILE_SIZE, Constant.TILE_SIZE, null);

                } else if ((System.nanoTime() - timeStart)/Constant.Tera > 4) { //disappeared in 4s
                    GameScene.bombCounter--;
                    state = 2;
                    update();

                } else {//exploding
                    state = 1;
                    //draw explosion
                    BombExplodeMap.getInstance().drawExplosion(g2,this);
                    BombExplodeMap.getInstance().update();
                }
            }
    }

    public void getBombImage() {
        try {
            for (int i = 0; i < 4; i++) {
                bomb[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Bomb/bomb" + (i + 1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefault() {
        this.direction = "bomb";
        this.state = 0; //0 is not explode, 1 is exploded, 2 is disappeared
    }

    public void update() {//count sprite
        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum != 4) {
                spriteNum++;
            } else
                spriteNum = 1;
            spriteCounter = 0;
        }
        bombList.removeIf(bomb -> bomb.state == 2);
    }
    // getter && setter

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Bomb> getBombList() {
        return bombList;
    }

    public int getBombCounter() {
        return bombCounter;
    }
    public void setBombCounter(int bombCounter) {
        this.bombCounter = bombCounter;
    }

    //get radius
    public void setBombRadius(int radius) {
        bombRadius += radius;
    }
    public int getBombRadius() {
        return bombRadius;
    }
    // set radius

    public int getState(){
        return state;
    }
}
