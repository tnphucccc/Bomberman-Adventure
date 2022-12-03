package Entity;

import Controls.KeyHandler;
import GUI.Camera;
import Variables.Constant;
import Variables.Time;
import com.sun.security.jgss.GSSUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class Bomb extends Entity {
    public static int bombSize = 5;
    KeyHandler keyH;

    boolean spacePressed = false;
    ArrayList<Bomb> bombList = new ArrayList<>(bombSize);

    private int x, y;

    private String key = "";
    private int bombCounter = 0;

    Image bombImage, bombExplodeImage, currentImage;

    ArrayList<Double> timePlaced = new ArrayList<>(bombSize);

    public Bomb(KeyHandler keyH) {
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        try {
            URL url = Objects.requireNonNull(getClass().getResource("/Bomb/Bomb.gif"));
            ImageIcon icon = new ImageIcon(url);
            bombImage = icon.getImage();

            URL url2 = Objects.requireNonNull(getClass().getResource("/Bomb/start1.png"));
            ImageIcon icon2 = new ImageIcon(url2);
            bombExplodeImage = icon2.getImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Explosion Timer
    public boolean checkExplosion(){
        for (int i = 0; i < bombList.size(); i++) {
            double delta = Time.getTime() - timePlaced.get(i);
            if (delta > 3) {
                return true;
            }
        }
        return false;
    }


    public boolean checkAvailable(int x, int y) {
        for (Bomb bomb : bombList) {
            if (bomb.x == x && bomb.y == y) {
                return false;
            }
        }
        return true;
    }

    //Check if when the bomb is placed
    public boolean checkPlaceBomb(int x, int y) {
        if (bombCounter < bombSize) {
            if (keyH.spacePressed) {
                spacePressed = true;
            }
            if (!keyH.spacePressed && spacePressed) {
                spacePressed = false;
                if (checkAvailable(x, y)) {
                    timePlaced.add(Time.getTime());
                    return true;
                }
            }
        }
        return false;
    }

    public void update(int x, int y) {
        key = "space";

        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;

        if(checkPlaceBomb(this.x, this.y)){
            bombList.add(bombCounter, new Bomb(keyH));
            bombList.get(bombCounter).update(this.x, this.y);
            System.out.println("Bomb" + bombCounter + " placed at " + timePlaced.get(bombCounter));
            bombCounter++;
        }

        //Animation
        if(checkExplosion()){
            currentImage = bombExplodeImage;
            System.out.println("Bomb Exploded");
        }
        else {
            currentImage = bombImage;
            System.out.println("Bomb not exploded");
        }
    }
    public void draw(Graphics2D g2) {
        if (bombList != null) {
            if (key.equals("space")) {
                    if (Camera.canDraw(this.x, this.y)) {
                        g2.drawImage(currentImage, Camera.getXCord(this.x), Camera.getYCord(this.y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
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
            System.out.println("explode");
    }
}

