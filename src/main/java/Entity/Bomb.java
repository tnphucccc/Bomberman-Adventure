package Entity;

import Controls.KeyHandler;
import GUI.Camera;
import Variables.Constant;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class Bomb extends Entity {
    public static int bombSize = 5;
    KeyHandler keyH;
    boolean spacePressed = false;
    ArrayList<Bomb> bombList = new ArrayList<>(bombSize);

    private long timeStart = 0l;
    private long timeElapsed = 3000000000l;
    private int x, y;


    private String key = "";
    private int bombCounter = 0;
    public boolean flag = false;

    //KeyHandler

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
        key = "space";
        timeStart = System.nanoTime()/1000000000;
        // round x and y so the bomb is placed in the middle of the tile
        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;
        if (bombCounter < bombSize) {
            if (keyH.spacePressed) {
                spacePressed = true;
                timeStart=System.currentTimeMillis();
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

    //draw bomb on the map with gif
    public void draw(Graphics2D g2) {
        if (bombList != null) {

            //Image img = null;
            if (key.equals("space")) {
                //load Bomb.gif from resources
                URL url = Objects.requireNonNull(getClass().getResource("/Bomb/Bomb.gif"));
                ImageIcon icon = new ImageIcon(url);
                Image img = icon.getImage();
                // img for the bomb initial

                if(timeElapsed>System.nanoTime()-timeStart){
                    g2.drawImage(img, Camera.getXCord(this.x), Camera.getYCord(this.y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
                } else {
                    // img for the bomb after 3 seconds
                    URL url2 = Objects.requireNonNull(getClass().getResource("/Bomb/start1.png"));
                    ImageIcon icon2 = new ImageIcon(url2);
                    Image img2 = icon2.getImage();
                    g2.drawImage(img2, Camera.getXCord(this.x), Camera.getYCord(this.y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
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
        // TODO Auto-generated method stub

    }

    public void update(double dt) {
        // TODO Auto-generated method stub
        if ((System.currentTimeMillis() - timeStart < 5000L)&&(System.currentTimeMillis() - timeElapsed > 1000L)) {
            System.out.println("explode");
        }
    }
}
