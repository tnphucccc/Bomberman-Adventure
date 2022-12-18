package GUI;
import Variables.Constant;
import javax.imageio.ImageIO;

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
            if (map[y + i][x] == 0 || map[y + i][x] == 3) {
                draw(g2, x, y + i);
            } else if (map[y + i][x] == 1 || map[y + i][x] == 4) {
                break;
            } else if (map[y + i][x] == 2) {
                draw(g2, x, y + i);
                breakBrick(x, y + i);
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
                breakBrick(x, y - i);
                break;
            }
        }

        //check right
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x + i] == 0 || map[y][x + i] == 3) {
                draw(g2, x + i, y);
            }
            else if (map[y][x + i] == 1 || map[y][x + i] == 4) {
                break;
            }
            else if (map[y][x + i] == 2) {
                draw(g2, x + i, y);
                breakBrick(x + i, y);
                break;
            }
        }

        //check left
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x - i] == 0 || map[y][x - i] == 3) {
                draw(g2, x - i, y);
            }
            else if (map[y][x - i] == 1 || map[y][x - i] == 4) {
                break;
            }
            else if (map[y][x - i] == 2) {
                draw(g2, x - i, y);
                breakBrick(x - i, y);
                break;
            }
        }
    }

    public void breakBrick(int x, int y) {
        if (map[y - 1][x] == 1 || map[y - 1][x] == 2) {
            map[y][x] = 3;//if there is brick or block above draw shadow
        } else map[y][x] = 0;
        if(map[y + 1][x] == 3){
            map[y + 1][x] = 0;//check if under brick is shadow, if shadow draw ground
        }
    }

    //for Debug
    public void printMap(int[][] array ){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void draw(Graphics2D g2, int x, int y) {
        int drawX = Camera.setXCord(x * Constant.TILE_SIZE);
        int drawY = Camera.setYCord(y * Constant.TILE_SIZE);

        g2.drawImage(up, drawX, drawY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
        // spriteCounter++;
        //         if (spriteCounter > 24) {
        //             if (spriteNum != 8) {
        //                 spriteNum++;
        //             } else
        //                 spriteNum = 5;
        //             spriteCounter = 0;
        //         }
    }

    public int[][] getMap(){
        return map;
    }

}
