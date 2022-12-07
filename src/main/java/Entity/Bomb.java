package Entity;

import Controls.KeyHandler;
import GUI.BombExplodeMap;
import GUI.Camera;
import Variables.Constant;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Bomb extends Entity {
    public static int bombSize = 100;
    KeyHandler keyH;
    boolean spacePressed = false;
    private ArrayList<Bomb> bombList = new ArrayList<>(bombSize);

    private long timeStart = 0L;
    private int x, y;
    private String key = "";
    private int bombCounter = 0;

    BombExplodeMap bombExplodeMap;
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
        getBombImage();
        setDefault();
    }

    public void update(int x, int y) {
        key = "space";
        timeStart = System.nanoTime();
        bombExplodeMap = new BombExplodeMap();
        // round x and y so the bomb is placed in the middle of the tile
        this.x = ((x + 16) / 48) * 48;
        this.y = ((y + 24) / 48) * 48;
        System.out.println(this.x + " " + this.y);
        if (bombCounter < bombSize) {
            if (keyH.spacePressed) {
                spacePressed = true;

            }
            if (!keyH.spacePressed && spacePressed) {
                spacePressed = false;

                if (checkAvailable(this.x, this.y)) {
                    bombList.add(bombCounter, new Bomb(keyH));
                    bombList.get(bombCounter).update(this.x, this.y);
                    System.out.println(this.x/48+" "+this.y/48);
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
            if (bomb.x == x && bomb.y == y) {
                return false;
            }
        }
        return true;
    }

    //draw bomb on the map with gif
    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();
        if (bombList != null) {
            //Image img = null;
            if (key.equals("space")) {
                long timeElapsed = 2000000000L;
                long timeDuration = 4000000000L;
                if (timeElapsed > System.nanoTime() - timeStart) {//planting
                    update();
                    g2.drawImage(img, Camera.getXCord(x), Camera.getYCord(y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);

                } else if (timeDuration<System.nanoTime()-timeStart) {//disappeared
                    state=2;
                    update();
                    bombList.remove(this);

                } else {//exploding
                    state = 1;
                    update();
                    // img for the bomb after 3 seconds
                    img = getBufferedImage(explode[0], explode[1], explode[2], explode[3],
                            explode[4], explode[5], explode[6], explode[7]);
                    g2.drawImage(img, Camera.getXCord(x), Camera.getYCord(y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
                    //draw the explosion


                    bombExplodeMap.draw(this.x,this.y, g2);
                    
                    state=1;
                }
            }
        }
    }

    // getter && setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public void getBombImage() {
        try {
            for (int i = 0; i < 4; i++) {
                bomb[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Bomb/bomb" + (i + 1) + ".png")));
            }
            for (int i = 0; i < 6; i++) {
                explode[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Bomb/start" + (i + 1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefault() {
        // TODO Auto-generated method stub
        this.direction = "bomb";
        this.state = 0;//0 is not explode, 1 is exploded, 2 is disappeared
    }

    public void update() {//count sprite
        // TODO Auto-generated method stub
        switch (state) {
            case 0 -> {
                spriteCounter++;
                if (spriteCounter > 8) {
                    if (spriteNum != 4) {
                        spriteNum++;
                    } else
                        spriteNum = 1;
                    spriteCounter = 0;
                }
            }
            case 1 -> {
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum != 8) {
                        spriteNum++;
                    } else
                        spriteNum = 5;
                    spriteCounter = 0;
                }
            }
        }
        bombList.removeIf(bomb -> bomb.state == 2);
    }
}
