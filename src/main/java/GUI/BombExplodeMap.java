package GUI;
import Variables.Constant;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class BombExplodeMap {
    private int[][] map;
    private int bombRadius = 2;
    public static BombExplodeMap instance;

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

    public void printMap(){
        for (int i = 0; i < Constant.MAX_WORLD_ROW; i++) {
            for (int j = 0; j < Constant.MAX_WORLD_COL; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void drawExplosion(int x, int y) {
        //explosion at bomb
        map[y][x] = 5;

        //check downward
        for (int i = 1; i <= bombRadius; i++) {
            if (map[y + i][x] == 0 || map[y + i][x] == 3) {
                map[y + i][x] = 5;
            } else if (map[y + i][x] == 2) {
                map[y + i][x] = 6;
            } else if (map[y + i][x] == 1 || map[y + i][x] == 4) {
                break;
            }
        }

        //check upward
        for (int i = 1; i <= bombRadius; i++) {
            if (map[y - i][x] == 0 || map[y - i][x] == 3) {
                map[y - i][x] = 5;
            } else if (map[y - i][x] == 2) {
                map[y - i][x] = 6;
            } else if (map[y - i][x] == 1 || map[y - i][x] == 4) {
                break;
            }
        }

        //check right
        for (int i = 1; i <= bombRadius; i++) {
            if (map[y][x+i] == 0 || map[y][x+i] == 3) {
                map[y][x+i] = 5;
            } else if (map[y][x+i] == 2) {
                map[y][x+i] = 6;
            } else if (map[y][x+i] == 1 || map[y][x+i] == 4) {
                break;
            }
        }

        //check left
        for (int i = 1; i <= bombRadius; i++) {
            if (map[y][x - i] == 0 || map[y][x - i] == 3) {
                map[y][x - i] = 5;
            } else if (map[y][x - i] == 2) {
                map[y][x - i] = 6;
            } else if (map[y][x - i] == 1 || map[y][x - i] == 4) {
                break;
            }
        }
    }

    public void deleteExplosion(){
        for (int i = 0; i < Constant.MAX_WORLD_ROW; i++) {
            for (int j = 0; j < Constant.MAX_WORLD_COL; j++) {
                if (map[i][j] == 5 ){
                    map[i][j] = 0;
                }
                if (map[i][j] == 6){
                    map[i][j] = 0;
                }
            }
        }
    }


//    }
    //draw 
//    public void draw(int x,int y,Graphics2D g2) {
//        this.x = (x)/48+x%48;
//        this.y = (y)/48+y%48;
//
//        System.out.println("Location "+map[this.x][this.y]+" "+this.x+" "+this.y);
//
//        if(map[this.x][this.y-1]==0 || map[this.x][this.y-1]==3) {
//            System.out.println(map[this.x][this.y-1]+" up");
//            drawBombExplode(g2, up, this.x, this.y-1);
//        }
//        if(map[this.x][this.y+1]==0 || map[this.x][this.y+1]==3) {
//            System.out.println(map[this.x][this.y+1]+" down");
//            drawBombExplode(g2, up, this.x, this.y+1);
//        }
//        if(map[this.x-1][this.y]==0 || map[this.x-1][this.y]==3) {
//            System.out.println(map[this.x-1][this.y]+" left");
//            drawBombExplode(g2, up, this.x-1, this.y);
//        }
//        if(map[this.x+1][this.y]==0 || map[this.x+1][this.y]==3) {
//            System.out.println(map[this.x+1][this.y]+" right");
//            drawBombExplode(g2, up, this.x+1, this.y);
//        }
//
//    }
//
//    //get map
//    public void drawBombExplode(Graphics2D g2,BufferedImage img,int x,int y) {
//
//        g2.drawImage(img, Camera.getXCord(x*48), Camera.getYCord(y*48), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
//                Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
//    }
    public int[][] getMap() {
        return map;
    }
//    //set map
//    public void setMap(int x,int y,int value) {
//        map[x][y] = value;
//    }
//    //draw bomb explosion

}
