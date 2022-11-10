package Entity;

import GUI.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Mob extends Entity{
    gamePanel gamepanel;

    public Mob(gamePanel gamepanel){
        this.gamepanel=gamepanel;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefault();
        getMobImage();
    }
    public void setDefault() {
        this.x = 192;
        this.y = 224;
        speed = 1;
        direction = "down";
    }
    public void mobMove() {
        collisionOn = false;
        gamepanel.cCheck.checkTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
        } else{
            switch (direction) {
                case "up" -> direction = "down";
                case "down" -> direction = "up";
                case "left" -> direction = "right";
                case "right" -> direction = "left";
            }
        }
        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum != 4) {
                spriteNum++;
            } else
                spriteNum = 1;
            spriteCounter = 0;
        }

        // chua doi huong
    }
    public void getMobImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_up2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_up3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_up4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_down2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_down3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_down4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_left3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_left4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_right2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_right3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_right4.png")));
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
        /*
         g2.setColor(Color.WHITE);
         g2.fillRect(x,y,gamepanel.original_tile_size,gamepanel.original_tile_size);
        */

        BufferedImage img=null;
        switch (direction) {
            case "up" -> img = getBufferedImage(null, up1, up2, up3, up4);
            case "down" -> img = getBufferedImage(null, down1, down2, down3, down4);
            case "left" -> img = getBufferedImage(null, left1, left2, left3, left4);
            case "right" -> img = getBufferedImage(null, right1, right2, right3, right4);
        }
        g2.drawImage(img, x, y, gamepanel.original_tile_size * gamePanel.scale,
                gamepanel.original_tile_size * gamePanel.scale, null);
    }

    private BufferedImage getBufferedImage(BufferedImage img,
                                           BufferedImage img1,
                                           BufferedImage img2,
                                           BufferedImage img3,
                                           BufferedImage img4) {
        if (spriteNum == 1) {
            img = img1;
        }
        else if (spriteNum == 2) {
            img = img2;
        }
        else if (spriteNum == 3) {
            img = img3;
        }
        else if (spriteNum == 4) {
            img = img4;
        }
        return img;
    }
}

