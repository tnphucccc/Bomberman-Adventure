package Entity;

import Controls.KeyHandler;
import GUI.GameScene;
import Variables.Constant;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class Bomb extends Entity {
    public static int bombSize = 5;
    ArrayList<Bomb> bombList = new ArrayList<>(bombSize);
    private int bombCounter = 0;

    private int x, y;
    public int screenX, screenY;

    private long timeStart = 0L;
    private final long timeElapsed = 50000000000L;

    private String key = "";
    boolean spacePressed = false;

    public boolean canDrawn = false;
    public Image bombImg, bombExplodeImg, currentBombImg;
    KeyHandler keyH;

    public Bomb(KeyHandler keyH) {
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        try{
            URL url1 = Objects.requireNonNull(getClass().getResource("/Bomb/Bomb.gif"));
            ImageIcon icon1 = new ImageIcon(url1);
            bombImg = icon1.getImage();

            URL url2 = Objects.requireNonNull(getClass().getResource("/Bomb/start1.png"));
            ImageIcon icon2 = new ImageIcon(url2);
            bombExplodeImg = icon2.getImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkExplosion(){
        if (timeElapsed > System.nanoTime() - timeStart){
            currentBombImg = bombImg;
        } else {
            currentBombImg = bombExplodeImg;
        }
    }

    public void update(int x, int y) {
        key = "space";
        timeStart = System.nanoTime()/1000000000;

        // round x and y so the bomb is placed in the middle of the tile
        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;

        if (bombCounter < bombSize) {

            if (keyH.spacePressed) {
                spacePressed = true;
                timeStart = System.currentTimeMillis();
            }

            if (!keyH.spacePressed && spacePressed) {
                spacePressed = false;

                if (checkAvailable(this.x, this.y)) {
                    bombList.add(bombCounter, new Bomb(keyH));
                    bombList.get(bombCounter).update(this.x, this.y);
                    bombCounter++;

                    System.out.println("Bomb Placed:" + bombCounter);
                    timeStart = System.nanoTime();

                } else {
                    System.out.println("Bomb Cannot Be Placed");
                }
            }
        }
        checkExplosion(); //Check time, if time is up, change image to explosion

        if ((System.currentTimeMillis() - timeStart < 5000L) && (System.currentTimeMillis() - timeElapsed > 1000L)) {
            System.out.println("explode");
        }
    }

    // check if the tile is available
    public boolean checkAvailable(int x, int y) {
        for (Bomb bomb : bombList) {
            if (bomb.x == x && bomb.y == y) {
                return false;
            }
        }
        return true;
    }

    public void draw(Graphics2D g2) {
        drawCord(this.x, this.y);

        if (bombList != null) {
            if (key.equals("space")) {
                if (canDrawn) {
                    g2.drawImage(currentBombImg, screenX, screenY, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
                }
            }
        }
    }

    public void drawCord(int x, int y){
        screenX = x - GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X;
        screenY = y - GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y;

        //Create a draw area for the bomb
        if (x + Constant.TILE_SIZE > GameScene.getPlayer().x - Constant.PLAYER_SCREEN_X &&
                x - Constant.TILE_SIZE < GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X &&
                y + Constant.TILE_SIZE > GameScene.getPlayer().y - Constant.PLAYER_SCREEN_Y &&
                y - Constant.TILE_SIZE < GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y)
        {
            canDrawn = true;
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
        // TODO Auto-generated method stub

    }

}
