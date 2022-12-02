package Entity;

import Controls.KeyHandler;
import GUI.GameScene;
import Variables.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class Bomb extends Entity {
    public static int bombSize = 5;
    public BufferedImage bombImage;

    KeyHandler keyH;

    boolean spacePressed = false;

    ArrayList<Bomb> bombList = new ArrayList<>(bombSize);

    public int roundX, roundY;

    private String key = " ";
    private int bombCounter = 0;
    
    public Bomb(KeyHandler keyH) {
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        

    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;

        key = "space";
        // round x and y so the bomb is placed in the middle of the tile
        roundX = ((x + 16) / 48) * 48;
        roundY = ((y + 24) / 48) * 48;

        if (bombCounter < bombSize) {
            if (keyH.spacePressed) {
                spacePressed = true;
            }

            if (!keyH.spacePressed && spacePressed) {
                spacePressed = false;

                if (checkAvailable(roundX, roundY)) {
                    bombList.add(bombCounter, new Bomb(keyH));
                    bombList.get(bombCounter).update(roundX, roundY);

                    bombCounter++;

                    System.out.println("Bomb Placed:" + bombCounter);
                } else {
                    System.out.println("Bomb Cannot Be Placed");
                }
            }
        }
    }

    // check if the tile is available
    public boolean checkAvailable(int x, int y) {
        for (Bomb bomb : bombList) {
            if (bomb.roundX == x && bomb.roundY == y) {
                return false;
            }
        }
        return true;
    }


    public void draw(Graphics2D g2) {
        if (bombList != null) {
            //Image img = null;
            if (key.equals("space")) {

                int screenX = this.x - GameScene.getPlayer().getX() + Constant.PLAYER_SCREEN_X;
                int screenY = this.y - GameScene.getPlayer().getY() + Constant.PLAYER_SCREEN_Y;
              
                    // wake up dude
                    
               // gif 

                if (this.x + Constant.TILE_SIZE > GameScene.getPlayer().getX() - Constant.PLAYER_SCREEN_X &&
                        this.x - Constant.TILE_SIZE < GameScene.getPlayer().getX() + Constant.PLAYER_SCREEN_X &&
                        this.y + Constant.TILE_SIZE > GameScene.getPlayer().getY() - Constant.PLAYER_SCREEN_Y &&
                        this.y - Constant.TILE_SIZE < GameScene.getPlayer().getY() + Constant.PLAYER_SCREEN_Y) {
                            URL url = Objects.requireNonNull(getClass().getResource("/Bomb/Bomb.gif"));
                            ImageIcon icon = new ImageIcon(url);
                            Image img = icon.getImage();
                    g2.drawImage(img, screenX, screenY, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
                            System.out.println("Bomb Drawn");
                }
            }
        }
    }

    // getter && setter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public void setDefault() {

    }

    @Override
    public void update(double dt) {

    }
}

