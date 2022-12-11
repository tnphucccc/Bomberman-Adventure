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
    public static int bombRadius = 2;
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

    public void drawExplosion(int x, int y, Graphics2D g2) {
        x = x / Constant.TILE_SIZE;
        y = y / Constant.TILE_SIZE;

        //check downward
        for (int i = 1; i <= bombRadius; i++) {
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
        for (int i = 1; i <= bombRadius; i++) {
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
        for (int i = 1; i <= bombRadius; i++) {
            if (map[y][x + 1] == 0 || map[y][x + 1] == 3) {
                draw(g2, x + i, y);
            }
            else if (map[y][x + 1] == 1 || map[y][x + 1] == 4) {
                break;
            }
            else if (map[y][x + 1] == 2) {
                draw(g2, x + i, y);
                break;
            }
        }

        //check left
        for (int i = 1; i <= bombRadius; i++) {
            if (map[y][x - 1] == 0 || map[y][x - 1] == 3) {
                draw(g2, x - i, y);
            }
            else if (map[y][x - 1] == 1 || map[y][x - 1] == 4) {
                break;
            }
            else if (map[y][x - 1] == 2) {
                draw(g2, x - i, y);
                break;
            }
        }
    }

    public void draw(Graphics2D g2, int x, int y) {
        int drawX = Camera.getXCord(x * Constant.TILE_SIZE);
        int drawY = Camera.getYCord(y * Constant.TILE_SIZE);

        g2.drawImage(up, drawX, drawY, Constant.TILE_SIZE, Constant.TILE_SIZE, null);
    }
}
