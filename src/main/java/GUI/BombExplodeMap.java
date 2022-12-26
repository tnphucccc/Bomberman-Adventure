package GUI;

import Entity.Bomb;
import Entity.Entity;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BombExplodeMap extends Entity {
    public static BombExplodeMap instance;
    private final int[][] map;
    BufferedImage[][] mid = new BufferedImage[4][8], end = new BufferedImage[4][8];
    BufferedImage[] explode= new BufferedImage[8];

    public BombExplodeMap() {
        map = TileManager.getInstance().mapTileNum; //get map from TileManager
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 8; j++)
                    end[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/end" + (i) + (j + 1) + ".png")));
                    //mid[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/mid" + (i) + (j + 1) + ".png")));
            }
            for (int i = 0; i < 8; i++){
                explode[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/start" + (i + 1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        //check downward
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y + i][x] == 0 || map[y + i][x] == 3) {
                draw(g2, x, y, 4);
                draw(g2, x, y + i, 2);
            } else if (map[y + i][x] == 1 || map[y + i][x] == 4) {
                break;
            } else if (map[y + i][x] == 2) {
                draw(g2, x, y, 4);
                draw(g2, x, y + i, 2);
                breakBrick(x, y + i);
                break;
            }
        }

        //check upward
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y - i][x] == 0 || map[y - i][x] == 3) {
                draw(g2, x, y, 4);
                draw(g2, x, y - i, 0);
            } else if (map[y - i][x] == 1 || map[y - i][x] == 4) {
                break;
            } else if (map[y - i][x] == 2) {
                draw(g2, x, y, 4);
                draw(g2, x, y - i, 0);
                breakBrick(x, y - i);
                break;
            }
        }

        //check right
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x + i] == 0 || map[y][x + i] == 3) {
                draw(g2, x, y, 4);
                draw(g2, x + i, y, 1);
            } else if (map[y][x + i] == 1 || map[y][x + i] == 4) {
                break;
            } else if (map[y][x + i] == 2) {
                draw(g2, x, y, 4);
                draw(g2, x + i, y, 1);
                breakBrick(x + i, y);
                break;
            }
        }

        //check left
        for (int i = 1; i <= bomb.getBombRadius(); i++) {
            if (map[y][x - i] == 0 || map[y][x - i] == 3) {
                draw(g2, x, y, 4);
                draw(g2, x - i, y, 3);
            } else if (map[y][x - i] == 1 || map[y][x - i] == 4) {
                break;
            } else if (map[y][x - i] == 2) {
                draw(g2, x, y, 4);
                draw(g2, x - i, y, 3);
                breakBrick(x - i, y);
                break;
            }
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

    //for Debug
    public void printMap(int[][] array) {
        for (int[] ints : array) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void draw(Graphics2D g2, int x, int y, int i) {
        int drawX = Camera.setXCord(x * Constant.TILE_SIZE);
        int drawY = Camera.setYCord(y * Constant.TILE_SIZE);

        g2.drawImage(getImage(i), drawX, drawY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
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
    public BufferedImage getImage(int i){
        if (i == 4){
            return getBufferedImage(explode[0], explode[1], explode[2], explode[3],
                    explode[4], explode[5], explode[6], explode[7]);
        } else if(i==5){
            return getBufferedImage(mid[i][0], mid[i][1], mid[i][2], mid[i][3],
                    mid[i][4], mid[i][5], mid[i][6], mid[i][7]);
        }
        else{
            return getBufferedImage(end[i][0], end[i][1], end[i][2], end[i][3],
                    end[i][4], end[i][5], end[i][6], end[i][7]);
        }
    }
}
