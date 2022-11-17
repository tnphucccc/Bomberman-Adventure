package Entity;

import Controls.CollisionCheck;
import GUI.GameScence;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Mob extends Entity{
    GameScence gameScence;
    CollisionCheck cCheck = new CollisionCheck();

    String[] dir = {"down","up","right","left"};
    private final Random rand = new Random();
    public Mob( GameScence gameScence,int x,int y){
        this.gameScence=gameScence;
        this.x =x;
        this.y =y;
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

        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
        } else{
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
        if (spriteNum == 1) {
            return img1;
        }
        else if (spriteNum == 2) {
            return img2;
        }
        else if (spriteNum == 3) {
            return img3;
        }
        else if (spriteNum == 4) {
            return img4;
        }
        return null;
    }
}