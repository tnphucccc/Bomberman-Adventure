package Entity;

import Controls.SoundManager;
import GUI.Camera;
import GUI.TileManager;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BombExplodeMap extends Entity {
    public static BombExplodeMap instance;
    private final int[][] map;

    BufferedImage[][] end = new BufferedImage[9][8];
    BufferedImage[] explode= new BufferedImage[8];

    public int downLength, upLength, rightLength, leftLength;
    public int explosionSoundQueue = 0;
    SoundManager sound = new SoundManager("src/main/resources/Sound/bomb_explosion.wav");
    public BombExplodeMap() {
        map = TileManager.getInstance().mapTileNum; //get map from TileManager
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 8; j++)
                    end[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/end" + (i) + (j + 1) + ".png")));
            }
            for (int i = 4; i < 8; i++){
                for (int j = 0; j < 8; j++)
                    end[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/mid" + (i-4) + (j + 1) + ".png")));
            }
            for (int i = 0; i < 8; i++){
                explode[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/start" + (i + 1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.downLength = 0;
        this.upLength = 0;
        this.rightLength = 0;
        this.leftLength = 0;
    }

    public static BombExplodeMap getInstance() {
        if (instance == null) {
            instance = new BombExplodeMap();
        }
        return instance;
    }


    public void drawExplosion(Graphics2D g2, Bomb bomb) {
        int x = bomb.getX() / Constant.TILE_SIZE;
        int y = bomb.getY() / Constant.TILE_SIZE;

        if (explosionSoundQueue == 0) { //Sound Queue
            sound.playSound("src/main/resources/Sound/bomb_explosion.wav");
            explosionSoundQueue++;
        }

        //Check downward
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y + i][x] == 0 || map[y + i][x] == 3) { //Draw explosion if ground or shadow
                draw(g2, x, y, 8);
                if (map[y + (i+1)][x] == 1 || map[y + (i+1)][x] == 4){
                    draw(g2, x, y + i, 2);
                }
                else if(i!= bomb.getBombRadius()){
                    draw(g2, x, y + i, 6);
                }
                else draw(g2, x, y + i, 2);
            } else if (map[y + i][x] == 1 || map[y + i][x] == 4){ //Explosion not drawn if wall
                break;
            } else if (map[y + i][x] == 2) { //Break Brick
                draw(g2, x, y, 8);
                draw(g2, x, y + i, 6);
                draw(g2, x, y + i, 2);
                breakBrick(x, y + i);
                break;
            }
            downLength = i;;
        }

        //check upward
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y - i][x] == 0 || map[y - i][x] == 3) {
                draw(g2, x, y, 8);
                if (map[y - (i+1)][x] == 1 || map[y - (i+1)][x] == 4){
                    draw(g2, x, y - i, 0);
                }
                else if(i!= bomb.getBombRadius()){
                    draw(g2, x, y - i, 4);
                }
                else draw(g2, x, y - i, 0);
            } else if (map[y - i][x] == 1 || map[y - i][x] == 4){
                break;
            } else if (map[y - i][x] == 2) {
                draw(g2, x, y, 8);
                draw(g2, x, y - i, 4);
                draw(g2, x, y - i, 0);
                breakBrick(x, y - i);
                break;
            }
            upLength = i;
        }

        //check right
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x + i] == 0 || map[y][x + i] == 3) {
                draw(g2, x, y, 8);
                if (map[y][x + (i+1)] == 1 || map[y][x + (i+1)] == 4){
                    draw(g2, x + i, y, 1);
                }
                else if(i!= bomb.getBombRadius()){
                    draw(g2, x + i, y, 5);
                }
                else draw(g2, x + i, y, 1);
            } else if (map[y][x + i] == 1 || map[y][x + i] == 4){
                break;
            } else if (map[y][x + i] == 2) {
                draw(g2, x, y, 8);
                draw(g2, x + i, y, 5);
                draw(g2, x + i, y, 1);
                breakBrick(x + i, y);
                break;
            }
            rightLength = i;
        }

        //check left
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x - i] == 0 || map[y][x - i] == 3) {
                draw(g2, x, y, 8);
                if (map[y][x - (i+1)] == 1 || map[y][x - (i+1)] == 4){
                    draw(g2, x - i, y, 3);
                }
                else if(i!= bomb.getBombRadius()){
                    draw(g2, x - i, y, 7);
                }
                else draw(g2, x - i, y, 3);
            } else if (map[y][x - i] == 1 || map[y][x - i] == 4){
                break;
            } else if (map[y][x - i] == 2) {
                draw(g2, x, y, 8);
                draw(g2, x - i, y, 7);
                draw(g2, x - i, y, 3);
                breakBrick(x - i, y);
                break;
            }
            leftLength = i;
        }
    }

    public void breakBrick(int x, int y) {
        if (map[y - 1][x] == 1) {
            map[y][x] = 3;//check and draw shadow
        } else map[y][x] = 0;
        if (map[y + 1][x] == 3) {
            map[y + 1][x] = 0;//check if under brick is shadow, if shadow draw ground
        }
    }
    public void resetExplosion(){ //Reset explosion
        downLength = 0;
        upLength = 0;
        rightLength = 0;
        leftLength = 0;
    }

    public void draw(Graphics2D g2, int x, int y, int i) {
        int drawX = Camera.setXCord(x * Constant.TILE_SIZE);
        int drawY = Camera.setYCord(y * Constant.TILE_SIZE);
        g2.drawImage(getBossImage(i), drawX, drawY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }

    public int[][] getMap() {
        return map;
    }

    @Override
    public void update() {
        spriteCounter++;
        if (spriteCounter > 16) {
            if (spriteNum != 8) {
                spriteNum++;
            } else
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {

    }
    public BufferedImage getBossImage(int i){
        if (i == 8) {
            return getBufferedImage(explode[0], explode[1], explode[2], explode[3],
                    explode[4], explode[5], explode[6], explode[7]);
        }
        else{
            return getBufferedImage(end[i][0], end[i][1], end[i][2], end[i][3],
                    end[i][4], end[i][5], end[i][6], end[i][7]);
        }
    }
}
