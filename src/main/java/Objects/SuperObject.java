package Objects;

import GUI.Camera;
import GUI.TileManager;
import Variables.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, Constant.TILE_SIZE, Constant.TILE_SIZE);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    public int[][] map;

    public SuperObject() {
        map = TileManager.getInstance().mapTileNum;
    }

    public void draw(Graphics2D g2) {
        if(map[y / Constant.TILE_SIZE][x / Constant.TILE_SIZE] == 0 || map[y / Constant.TILE_SIZE][x / Constant.TILE_SIZE] == 3) {
            g2.drawImage(image, Camera.getXCord(x), Camera.getYCord(y), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                    Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
        }
    }
}
