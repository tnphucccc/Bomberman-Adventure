package Entity;

import Controls.CollisionCheck;
import GUI.Camera;
import Variables.Constant;
import GUI.GameScene;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Mob extends Entity {
    private final Random rand = new Random();
    CollisionCheck cCheck = new CollisionCheck();
    String[] dir = {"down", "up", "right", "left"};

    public Mob(int x, int y) {
        this.x = x;
        this.y = y;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 36;
        solidArea.height = 32;

        setDefault();
        getMobImage();
    }

    public void setDefault() {
        speed = 1;
        this.direction = "down";
        this.state =1;
    }

    @Override
    public void update() {
        collisionOn = false;
        cCheck.checkTile(this);
        if(GameScene.getBombList() != null){
            for(int i = 0; i < GameScene.getBombList().size(); i++)
                cCheck.checkBomb(GameScene.getBombList().get(i), this);
        }

        cCheck.checkMob(GameScene.getPlayer(), GameScene.getMobList());

        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }

        } else {
            this.direction = dir[rand.nextInt(4)];
        }

        spriteCounter++;
        if (state!=0){
            if (spriteCounter > 8) {
                if (spriteNum != 4) {
                    spriteNum++;
                } else
                    spriteNum = 1;
                spriteCounter = 0;
            }
        } else {
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum != 6) {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }
    }

    public void getMobImage() {
        try {
            for (int i = 0; i < 4; i++) {
                up[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobUpRight" + (i + 1) + ".png")));
                down[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobDownLeft" + (i + 1) + ".png")));
                left[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobDownLeft" + (i + 1) + ".png")));
                right[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobUpRight" + (i + 1) + ".png")));
            }
            for (int i = 0; i < 6; i++)
                die[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Player/player_die" + (i + 1) + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();

        if (state == 0) {
            //Mob die
            img = getBufferedImage(die[0], die[1], die[2], die[3], die[4], die[5]);
            g2.drawImage(img,Camera.setXCord(x), Camera.setYCord(y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                    Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
            speed = 0;
        } else {
            //Mob is alive
            g2.drawImage(img, Camera.setXCord(x), Camera.setYCord(y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                    Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
        }
    }
}