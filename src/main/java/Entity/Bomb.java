package Entity;

import Controls.SoundManager;
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
    private final int bombSize = GameScene.getBombSize();
    public static int bombRadius;
    private final ArrayList<Bomb> bombList = new ArrayList<>(bombSize);

    private long timeStart = 0L;
    public int bombExplosionTimer = 2;
    public int bombExplosionTimerMax = 4;

    private int x, y;
    private String key = "";

    public BombExplodeMap bombExplodeMap;
    SoundManager sound1 = new SoundManager("src/main/resources/Sound/put_bombs.wav");
    SoundManager sound2 = new SoundManager("src/main/resources/Sound/bomb_explosion.wav");
    int explosionQueue, plantSoundQueue;

    public Bomb(int x, int y, int radius, BombExplodeMap bombExplodeMap) {
        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;
        this.bombExplodeMap = bombExplodeMap;
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
        update();

    }
    public void getBombImage() {//Load asset
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
        explosionQueue = 0;
        plantSoundQueue = 0;
    }

    public void spriteCounter() {//count sprite
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

    public void update() {
        key = "space";
        timeStart = System.nanoTime();
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        if (key.equals("space")) {
            if ((System.nanoTime() - timeStart)/Constant.Tera < bombExplosionTimer) {//planting for 2s
                if (plantSoundQueue == 0) { //Sound Queue
                    sound1.playSound("src/main/resources/Sound/put_bombs.wav");
                    plantSoundQueue++;
                }

                spriteCounter();
                g2.drawImage(img, Camera.setXCord(x), Camera.setYCord(y), Constant.TILE_SIZE, Constant.TILE_SIZE, null);

            } else if ((System.nanoTime() - timeStart)/Constant.Tera > bombExplosionTimerMax) { //disappeared in 4s
                GameScene.setBombCounter(GameScene.getBombCounter() - 1);
                state = 2;
                spriteCounter();
                bombExplodeMap.resetExplosion();

            } else {//exploding
                if (explosionQueue == 0) { //Sound Queue
                    sound2.playSound("src/main/resources/Sound/bomb_explosion.wav");
                    explosionQueue++;
                }
                state = 1;
                bombExplodeMap.drawExplosion(g2,this); //draw explosion
                bombExplodeMap.update();
            }
        }
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

    public int getBombRadius() {
        return bombRadius;
    }
    // set radius

    public int getState(){
        return state;
    }
}
