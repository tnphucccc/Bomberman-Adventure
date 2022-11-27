package Entity;

import Controls.CollisionCheck;
import Variables.Constant;

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
        solidArea.height = 36;
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
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobUp-1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobUp-2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobUp-3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobUp-4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobDown-1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobDown-2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobDown-3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobDown-4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobLeft-1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobLeft-2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobLeft-1.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobLeft-2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobRight-1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobRight-2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobRight-1.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Mob/MobRight-2.png")));
            die1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_die1.png")));
            die2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_die2.png")));
            die3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_die3.png")));
            die4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_die4.png")));
            die5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_die5.png")));
            die6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_die6.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}