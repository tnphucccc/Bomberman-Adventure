package Entity;

import Controls.CollisionCheck;
import Controls.KeyHandler;
import GUI.GameScene;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler keyH;
    CollisionCheck cCheck = new CollisionCheck();

    public Player(KeyHandler keyH, int state) {
        this.state = state;
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefault();
        getPlayerImage();

    }

    @Override
    public void setDefault() {
        x = 48 + Constant.tileSize;
        y = 32 + Constant.tileSize;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage() {
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

    @Override
    public void update(double dt) {
        if ((keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) && state == 1) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            collisionOn = false;
            cCheck.checkTile(this);
            int objIndex = GameScene.cCheck.checkObject(this, true);
            pickUpObject(objIndex);
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
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
        } else if (state == 0) {
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum != 6) {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = GameScene.Object[i].name;
            switch (objName) {
                case "BlastRadius" -> {
                    Bomb.bombSize += 1;
                    GameScene.Object[i] = null;
                }
                case "SpeedIncrease" -> {
                    speed += 1;
                    GameScene.Object[i] = null;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        //Image img1 = null;

        switch (direction) {
            case "up" -> img = getBufferedImage(up1, up2, up3, up4);
            case "down" -> img = getBufferedImage(down1, down2, down3, down4);
            case "left" -> img = getBufferedImage(left1, left2, left3, left4);
            case "right" -> img = getBufferedImage(right1, right2, right3, right4);
        }
        if (state == 0) {
            img = getBufferedImage(die1, die2, die3, die4, die5, die6);
            g2.drawImage(img, x, y, Constant.original_tile_size * Constant.scale,
                    Constant.original_tile_size * Constant.scale, null);
            speed = 0;
        } else {
            //PLayer is alive
            g2.drawImage(img, x, y, Constant.original_tile_size * Constant.scale,
                    Constant.original_tile_size * Constant.scale, null);
            //if (img1 != null) img1.flush();
        }
    }

    private BufferedImage getBufferedImage(BufferedImage die1,
                                           BufferedImage die2,
                                           BufferedImage die3,
                                           BufferedImage die4,
                                           BufferedImage die5,
                                           BufferedImage die6) {
        switch (spriteNum) {
            case 1 -> {
                return die1;
            }
            case 2 -> {
                return die2;
            }
            case 3 -> {
                return die3;
            }
            case 4 -> {
                return die4;
            }
            case 5 -> {
                return die5;
            }
            case 6 -> {
                return die6;
            }
        }
        return null;
    }

    private BufferedImage getBufferedImage(BufferedImage img1,
                                           BufferedImage img2,
                                           BufferedImage img3,
                                           BufferedImage img4) {
        if (spriteNum == 1) {
            return img1;
        }
        if (spriteNum == 2) {
            return img2;
        }
        if (spriteNum == 3) {
            return img3;
        }
        if (spriteNum == 4) {
            return img4;
        }
        return null;
    }
}