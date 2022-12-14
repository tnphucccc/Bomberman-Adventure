package GUI;
import Variables.Constant;
import javax.imageio.ImageIO;

import Controls.CollisionCheck;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import Entity.Bomb;
public class BombExplodeMap {
    private int[][] map;
    
    public static BombExplodeMap instance;
    private int x,y;
    BufferedImage up;
    
    public BombExplodeMap() {
        map = TileManager.getInstance().mapTileNum; //get map from TileManager
        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/start4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BombExplodeMap getInstance(){
        if (instance == null){
            instance = new BombExplodeMap();
        }
        return instance;
    }

    public void drawExplosion(Graphics2D g2,Bomb bomb) {
        this.x = bomb.getX() / Constant.TILE_SIZE;
        this.y = bomb.getY() / Constant.TILE_SIZE;

        //check downward
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y + i][x] == 0 || map[y + i][x] == 3 ) {
                draw(g2, x, y + i);
            }
            else if (map[y + i][x] == 1 || map[y + i][x] == 4) {
                break;
            }
            else if (map[y + i][x] == 2) {
                draw(g2, x, y + i);
                break;
            }
        }

        //check upward
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y - i][x] == 0 || map[y - i][x] == 3 ) {
                draw(g2, x, y - i);
            }
            else if (map[y - i][x] == 1 || map[y - i][x] == 4) {
                break;
            }
            else if (map[y - i][x] == 2) {
                draw(g2, x, y - i);
                break;
            }
        }

        //check right
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x + 1] == 0 || map[y][x + 1] == 3) {
                draw(g2, x + i, y);
            }
            else if (map[y][x + i] == 1 || map[y][x + i] == 4) {
                break;
            }
            else if (map[y][x + i] == 2) {
                draw(g2, x + i, y);
                break;
            }
        }

        //check left
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x - 1] == 0 || map[y][x - 1] == 3) {
                draw(g2, x - i, y);
            }
            else if (map[y][x - i] == 1 || map[y][x - i] == 4) {
                break;
            }
            else if (map[y][x - i] == 2) {
                draw(g2, x - i, y);
                break;
            }
        }
    }

    public void draw(Graphics2D g2, int x, int y) {
        int drawX = Camera.getXCord(x * Constant.TILE_SIZE);
        int drawY = Camera.getYCord(y * Constant.TILE_SIZE);
        // spriteCounter++;
        //         if (spriteCounter > 24) {
        //             if (spriteNum != 8) {
        //                 spriteNum++;
        //             } else
        //                 spriteNum = 5;
        //             spriteCounter = 0;
        //         }
        g2.drawImage(up, drawX, drawY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
