package Entity;

import Controls.KeyHandler;
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
    private long timeElapsed = 5000000000l;
    private long currentTime = 0l;
    private int x, y;

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
        this.timeStart = System.nanoTime();
       // this.key = "space";
        // round x and y so the bomb is placed in the middle of the tile
        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;
        if (bombCounter < bombSize) {
            if (keyH.spacePressed) {
                spacePressed = true;

            }
            if (!keyH.spacePressed && spacePressed) {
                spacePressed = false;
                
                if (checkAvailable(this.x, this.y)) {
                    
                    bombList.add(bombCounter, new Bomb(keyH));
                    bombList.get(bombCounter).update(this.x, this.y);
                    bombCounter++;
                    System.out.println("Bomb Placed:" + bombCounter);
                    System.out.println(timeStart);
                    

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
            //if (this.key.equals("space")) {
                //load Bomb.gif from resources
                
                // img for the bomb initial 
                currentTime = System.nanoTime()-timeStart;
                if(timeElapsed>currentTime){
                    URL url = Objects.requireNonNull(getClass().getResource("/Bomb/Bomb.gif"));
                    ImageIcon icon = new ImageIcon(url);
                    Image img = icon.getImage();
                    g2.drawImage(img, this.x, this.y, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                        Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
                        System.out.println(this.timeStart);
                } else {
                    // img for the bomb after 3 seconds
                    URL url2 = Objects.requireNonNull(getClass().getResource("/Bomb/start1.png"));
                    ImageIcon icon2 = new ImageIcon(url2);
                    Image img2 = icon2.getImage();
                    g2.drawImage(img2, this.x, this.y, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                        Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
                        System.out.println(this.timeStart);
                }
                
                
            }
        //}
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

    @Override
    public void update(double dt) {
        // TODO Auto-generated method stub

    }
}

