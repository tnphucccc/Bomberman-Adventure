package Entity;

import Controls.CollisionCheck;
import Controls.KeyHandler;
import GUI.*;
import GUI.Window;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler keyH = Window.getKeyH();
    public static Player instance;

    public static Player getInstance(){
        if (Player.instance == null){
            Player.instance = new Player();
        }
        return Player.instance;
    }
    public Player() {

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;
        setDefault();
        getPlayerImage();
        state = 1;
    }

    public void setDefault() {
        if(GameScene.getMapID() == 2) { //Player cord at Map 02
            x = Constant.TILE_SIZE * 8;
            y = Constant.TILE_SIZE * 5;
            speed = 4;
        }

        if(GameScene.getMapID() == 1) { //Player cord at Map01
            x = Constant.TILE_SIZE * 2;
            y = Constant.TILE_SIZE * 2;
            speed = 2;
        }
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
    @Override
    public void update() {
        collisionOn = false;
        if(GameScene.getBombList() != null){
            for(Bomb b : GameScene.getBombList()){
                CollisionCheck.getInstance().checkBomb(b,this);
            }
        }
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
            

            //Check collision with Tiles
            CollisionCheck.getInstance().checkTile(this);

            //Check collision with Items
            int objIndex = GameScene.cCheck.checkObject(this, true);
            pickUpObject(objIndex);

            //Check Collision with Bomb
            

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
                    GameScene.bombSize += 1;
                    GameScene.Object[i] = null;
                }
                case "SpeedIncrease" -> {
                    speed += 1;
                    GameScene.Object[i] = null;
                }
                case "Door" ->{
                    //if(GameScene.getMobCounter()==0) {
                        Window.getWindow().changeState(2); //Change to next map
                        TileManager.getInstance().clearMap();
                    //}
                }
                case "BlastRadius" ->{
                    bomb.setBombRadius(1);
                    GameScene.Object[i] = null;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        if (state == 0) { //Player Die
            img = getBufferedImage(die[0], die[1], die[2], die[3], die[4], die[5]);
            g2.drawImage(img, Camera.setXPlayerCord(x), Camera.setYPlayerCord(y), Constant.TILE_SIZE, Constant.TILE_SIZE, null);
            speed = 0;
        } else { // Player alive
            g2.drawImage(img, Camera.setXPlayerCord(x), Camera.setYPlayerCord(y), Constant.TILE_SIZE, Constant.TILE_SIZE, null);
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