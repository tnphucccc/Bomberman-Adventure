package Entity;

import Controls.CollisionCheck;
import Variables.Constant;
import GUI.GameScene;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Mob extends Entity {
    private int mobSize = 3;
    ArrayList<Mob> mobList = new ArrayList<>(mobSize);
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
    }

    @Override
    public void update(double dt) {
        collisionOn = false;
        cCheck.checkTile(this);
        cCheck.checkBomb(GameScene.getBombList(), this);
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
        if (spriteCounter > 8) {
            if (spriteNum != 4) {
                spriteNum++;
            } else
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void getMobImage() {
        try {
            for (int i = 0; i < 4; i++) {
                up[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobRight-" + (i + 1) + ".png")));
                down[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobLeft-" + (i + 1) + ".png")));
                left[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobLeft-" + (i + 1) + ".png")));
                right[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobRight-" + (i + 1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        g2.drawImage(img, x, y, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
    }
}