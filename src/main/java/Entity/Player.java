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
        x = Constant.TILE_SIZE * 8; // at tile 16
        y = Constant.TILE_SIZE * 5; // at tile 16
        speed = 4;

        direction = "down";
    }

    public void getPlayerImage() {
        try {
            for (int i = 0; i < 4; i++) {
                up[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Player/player_up" + (i + 1) + ".png")));
                down[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Player/player_down" + (i + 1) + ".png")));
                left[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Player/player_left" + (i + 1) + ".png")));
                right[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Player/player_right" + (i + 1) + ".png")));
            }
            for (int i = 0; i < 6; i++)
                die[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Player/player_die" + (i + 1) + ".png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
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
            //check collision with tile, mob,object,bomb
            collisionOn = false;

            //Check collision with Tiles
            CollisionCheck.getInstance().checkTile(this);

            //Check collision with Items
            int objIndex = GameScene.cCheck.checkObject(this, true);
            pickUpObject(objIndex);

            //Check Collision with Bomb
            CollisionCheck.getInstance().checkBomb(GameScene.getBombList(), this);

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
                case "ExtraBomb" -> {
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
        BufferedImage img = getEntityImage();
        if (state == 0) {
            //Player die
            img = getBufferedImage(die[0], die[1], die[2], die[3], die[4], die[5]);
            g2.drawImage(img, Constant.PLAYER_SCREEN_X, Constant.PLAYER_SCREEN_Y, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                    Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
            speed = 0;
        } else {
            //PLayer is alive
            g2.drawImage(img, Constant.PLAYER_SCREEN_X, Constant.PLAYER_SCREEN_Y, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                    Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
            //if (img1 != null) img1.flush();
        }
    }

    //getter
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}